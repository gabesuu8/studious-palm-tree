package com.example.helloapp.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helloapp.ArticleDetailActivity
import com.example.helloapp.FavoritesActivity
import com.example.helloapp.OnboardingActivity
import com.example.helloapp.RapidTestTimerActivity
import com.example.helloapp.R
import com.example.helloapp.adapter.ArticleAdapter
import com.example.helloapp.data.Article
import com.example.helloapp.util.LanguageHelper
import com.example.helloapp.viewmodel.ArticleViewModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class ArticlesFragment : Fragment() {
    
    private val TAG = "ArticlesFragment"
    private var viewModel: ArticleViewModel? = null
    
    private var recyclerView: RecyclerView? = null
    private var emptyStateLayout: View? = null
    private var emptyStateText: TextView? = null
    private var suggestionText: TextView? = null
    private var adapter: ArticleAdapter? = null
    private var searchEditText: EditText? = null
    private var clearSearchButton: ImageView? = null
    private var categoryChipGroup: ChipGroup? = null
    private var disclaimerCard: View? = null
    private var latestSuggestion: String? = null

    private val disclaimerPrefs: SharedPreferences?
        get() = context?.getSharedPreferences(PREFS_ARTICLES, Context.MODE_PRIVATE)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_articles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        try {
            // Setup toolbar: single “Options” opens dialog with three labeled tabs
            val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar)
            toolbar.setOnMenuItemClickListener { menuItem ->
                if (menuItem.itemId == R.id.menu_options) {
                    showOptionsDialog()
                    true
                } else {
                    false
                }
            }
            
            Log.d(TAG, "Initializing views")
            recyclerView = view.findViewById(R.id.articlesRecyclerView)
            emptyStateLayout = view.findViewById(R.id.emptyStateLayout)
            emptyStateText = view.findViewById(R.id.emptyStateText)
            suggestionText = view.findViewById(R.id.suggestionText)
            searchEditText = view.findViewById(R.id.searchEditText)
            clearSearchButton = view.findViewById(R.id.clearSearchButton)
            categoryChipGroup = view.findViewById(R.id.categoryChipGroup)
            disclaimerCard = view.findViewById(R.id.disclaimerCard)
            
            // Show/hide disclaimer based on user preference
            if (disclaimerPrefs?.getBoolean(KEY_DISCLAIMER_DISMISSED, false) == true) {
                disclaimerCard?.visibility = View.GONE
            }
            view.findViewById<View>(R.id.disclaimerClose)?.setOnClickListener {
                val parent = disclaimerCard?.parent as? ViewGroup
                if (parent != null) {
                    TransitionManager.beginDelayedTransition(parent, AutoTransition().apply {
                        duration = 200
                    })
                }
                disclaimerCard?.visibility = View.GONE
                disclaimerPrefs?.edit()?.putBoolean(KEY_DISCLAIMER_DISMISSED, true)?.apply()
            }
            
            adapter = ArticleAdapter(
                onItemClick = { article -> openArticleDetail(article) },
                onFavoriteClick = { article -> toggleFavorite(article) }
            )
            
            recyclerView?.layoutManager = LinearLayoutManager(requireContext())
            recyclerView?.adapter = adapter

            Log.d(TAG, "Initializing ViewModel")
            viewModel = ViewModelProvider(
                requireActivity(),
                ArticleViewModel.AndroidViewModelFactory.getInstance(requireActivity().application)
            )[ArticleViewModel::class.java]

            // Setup search functionality
            setupSearch()
            
            // Setup category filter chips
            setupCategoryChips()

            Log.d(TAG, "Starting article observation")
            // Observe filtered articles and spelling suggestion together
            viewLifecycleOwner.lifecycleScope.launch {
                var isFirstLoad = true
                kotlinx.coroutines.flow.combine(
                    viewModel?.filteredArticles ?: kotlinx.coroutines.flow.flowOf(emptyList()),
                    viewModel?.suggestedSearchQuery ?: kotlinx.coroutines.flow.flowOf(null)
                ) { articles, suggestion ->
                    Pair(articles, suggestion)
                }.collect { (articles, suggestion) ->
                    Log.d(TAG, "Received articles: ${articles.size}, suggestion: $suggestion")
                    val searchQuery = viewModel?.searchQuery?.value ?: ""
                    
                    if (articles.isEmpty()) {
                        recyclerView?.visibility = View.GONE
                        emptyStateLayout?.visibility = View.VISIBLE
                        
                        if (searchQuery.isNotBlank()) {
                            emptyStateText?.text = getString(R.string.no_search_results, searchQuery)
                            latestSuggestion = suggestion
                            suggestionText?.visibility = if (suggestion != null) {
                                View.VISIBLE
                            } else {
                                View.GONE
                            }
                            suggestionText?.text = suggestion?.let { getString(R.string.did_you_mean, it) }
                        } else {
                            latestSuggestion = null
                            emptyStateText?.text = getString(R.string.no_articles)
                            suggestionText?.visibility = View.GONE
                            if (isFirstLoad) {
                                isFirstLoad = false
                                Log.d(TAG, "First load - fetching articles")
                                viewModel?.fetchAndSaveArticles()
                            }
                        }
                    } else {
                        recyclerView?.visibility = View.VISIBLE
                        emptyStateLayout?.visibility = View.GONE
                        submitArticlesPreservingScroll(articles)
                        isFirstLoad = false
                    }
                }
            }

            suggestionText?.setOnClickListener {
                latestSuggestion?.let { suggested ->
                    viewModel?.setSearchQuery(suggested)
                    searchEditText?.setText(suggested)
                    adapter?.setSearchKeywords(suggested)
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error in onViewCreated", e)
        }
    }

    private fun setupSearch() {
        searchEditText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s?.toString() ?: ""
                viewModel?.setSearchQuery(query)
                adapter?.setSearchKeywords(query)
                clearSearchButton?.isVisible = query.isNotEmpty()
            }
            
            override fun afterTextChanged(s: Editable?) {}
        })
        
        searchEditText?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchEditText?.clearFocus()
                true
            } else {
                false
            }
        }
        
        clearSearchButton?.setOnClickListener {
            searchEditText?.text?.clear()
            viewModel?.setSearchQuery("")
            adapter?.setSearchKeywords("")
            clearSearchButton?.visibility = View.GONE
        }
    }
    
    private fun setupCategoryChips() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel?.allCategories?.collect { categories ->
                categoryChipGroup?.removeAllViews()
                
                categories.forEach { category ->
                    val chip = Chip(requireContext()).apply {
                        text = category
                        isCheckable = true
                        isCheckedIconVisible = false
                        setChipBackgroundColorResource(R.color.chip_background_color)
                        setOnCheckedChangeListener { _, isChecked ->
                            if (isChecked) {
                                viewModel?.setSelectedCategory(category)
                            } else {
                                viewModel?.setSelectedCategory(null)
                            }
                        }
                    }
                    categoryChipGroup?.addView(chip)
                }
            }
        }
    }

    private fun submitArticlesPreservingScroll(articles: List<Article>) {
        val rv = recyclerView ?: run {
            adapter?.submitList(articles)
            return
        }
        val lm = rv.layoutManager as? LinearLayoutManager
        if (lm == null) {
            adapter?.submitList(articles)
            return
        }

        val firstVisible = lm.findFirstVisibleItemPosition().coerceAtLeast(0)
        val firstView = rv.getChildAt(0)
        val topOffset = firstView?.top ?: 0

        adapter?.submitList(articles) {
            if (articles.isEmpty()) return@submitList
            val targetPos = firstVisible.coerceAtMost(articles.lastIndex)
            lm.scrollToPositionWithOffset(targetPos, topOffset)
        }
    }

    private fun openArticleDetail(article: Article) {
        val intent = Intent(requireContext(), ArticleDetailActivity::class.java)
        intent.putExtra("article", article)
        startActivity(intent)
    }
    
    private fun toggleFavorite(article: Article) {
        viewModel?.toggleFavorite(article)
        val message = if (article.isFavorite) {
            getString(R.string.removed_from_favorites)
        } else {
            getString(R.string.added_to_favorites)
        }
        recyclerView?.let { Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show() }
    }

    /** Shows three labeled options (Language, Favorites, Rapid test timer) when user taps the three dots. */
    private fun showOptionsDialog() {
        val ctx = context ?: return
        val items = arrayOf(
            getString(R.string.language),
            getString(R.string.favorites),
            getString(R.string.rapid_test_timer_menu),
            getString(R.string.replay_tutorial)
        )
        val dialog = MaterialAlertDialogBuilder(ctx, R.style.Theme_HelloApp_AlertDialog)
            .setTitle(getString(R.string.menu_options))
            .setItems(items) { _, which ->
                when (which) {
                    0 -> showLanguageDialog()
                    1 -> startActivity(Intent(requireContext(), FavoritesActivity::class.java))
                    2 -> startActivity(Intent(requireContext(), RapidTestTimerActivity::class.java))
                    3 -> startActivity(Intent(requireContext(), OnboardingActivity::class.java))
                }
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
        dialog.listView?.apply {
            divider = android.graphics.drawable.ColorDrawable(Color.parseColor("#B2DFDB"))
            dividerHeight = 2
        }
    }

    private fun showLanguageDialog() {
        val ctx = context ?: return
        val languages = arrayOf(getString(R.string.english), getString(R.string.french))
        val languageCodes = arrayOf(LanguageHelper.ENGLISH, LanguageHelper.FRENCH)
        val currentLanguage = LanguageHelper.getLanguage(ctx)
        val currentIndex = languageCodes.indexOf(currentLanguage)

        val dialog = MaterialAlertDialogBuilder(ctx, R.style.Theme_HelloApp_AlertDialog)
            .setTitle(getString(R.string.language))
            .setSingleChoiceItems(languages, currentIndex) { dlg, which ->
                val selectedCode = languageCodes[which]
                if (currentLanguage != selectedCode) {
                    LanguageHelper.setLanguage(ctx, selectedCode)
                    dlg.dismiss()
                    // Reload articles in new language
                    viewModel?.fetchAndSaveArticles()
                    // Recreate activity to apply language change to UI
                    activity?.recreate()
                } else {
                    dlg.dismiss()
                }
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
        dialog.listView?.apply {
            divider = android.graphics.drawable.ColorDrawable(Color.parseColor("#B2DFDB"))
            dividerHeight = 2
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        recyclerView = null
        emptyStateLayout = null
        emptyStateText = null
        suggestionText = null
        adapter = null
        searchEditText = null
        clearSearchButton = null
        categoryChipGroup = null
        disclaimerCard = null
    }
    
    companion object {
        private const val PREFS_ARTICLES = "articles_fragment"
        private const val KEY_DISCLAIMER_DISMISSED = "disclaimer_dismissed"
    }
}
