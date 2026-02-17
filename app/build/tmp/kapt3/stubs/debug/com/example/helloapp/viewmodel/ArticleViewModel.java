package com.example.helloapp.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u000b\u0018\u00002\u00020\u0001:\u0001CB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u00104\u001a\u000205J\u000e\u00106\u001a\u0002072\u0006\u00108\u001a\u000209J\"\u0010:\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010;\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010<\u001a\u00020\u0007J\u000e\u0010=\u001a\u0002052\u0006\u0010>\u001a\u00020\u0007J\u0010\u0010?\u001a\u0002052\b\u0010@\u001a\u0004\u0018\u00010\u0007J\u000e\u0010A\u001a\u0002052\u0006\u0010B\u001a\u00020\fR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\'\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\'\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b0\n8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0012\u0010\u000eR\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0016\u001a\n \u0018*\u0004\u0018\u00010\u00170\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0019\u001a\u00020\u001a8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001d\u0010\u0010\u001a\u0004\b\u001b\u0010\u001cR\'\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b \u0010\u0010\u001a\u0004\b\u001f\u0010\u000eR\'\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b#\u0010\u0010\u001a\u0004\b\"\u0010\u000eR\u001b\u0010$\u001a\u00020%8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b(\u0010\u0010\u001a\u0004\b&\u0010\'R\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00070*\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0019\u0010-\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070*\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010,R#\u0010/\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\n8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b1\u0010\u0010\u001a\u0004\b0\u0010\u000eR,\u00102\u001a \u0012\u001c\u0012\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b030\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006D"}, d2 = {"Lcom/example/helloapp/viewmodel/ArticleViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_searchQuery", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_selectedCategory", "allArticles", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/helloapp/data/Article;", "getAllArticles", "()Lkotlinx/coroutines/flow/Flow;", "allArticles$delegate", "Lkotlin/Lazy;", "allCategories", "getAllCategories", "allCategories$delegate", "articleFetcher", "Lcom/example/helloapp/service/ArticleFetcher;", "context", "Landroid/content/Context;", "kotlin.jvm.PlatformType", "database", "Lcom/example/helloapp/data/AppDatabase;", "getDatabase", "()Lcom/example/helloapp/data/AppDatabase;", "database$delegate", "favoriteArticles", "getFavoriteArticles", "favoriteArticles$delegate", "filteredArticles", "getFilteredArticles", "filteredArticles$delegate", "repository", "Lcom/example/helloapp/repository/ArticleRepository;", "getRepository", "()Lcom/example/helloapp/repository/ArticleRepository;", "repository$delegate", "searchQuery", "Lkotlinx/coroutines/flow/StateFlow;", "getSearchQuery", "()Lkotlinx/coroutines/flow/StateFlow;", "selectedCategory", "getSelectedCategory", "suggestedSearchQuery", "getSuggestedSearchQuery", "suggestedSearchQuery$delegate", "symptomMap", "Lkotlin/Pair;", "fetchAndSaveArticles", "", "getArticleById", "Lkotlinx/coroutines/Job;", "id", "", "getSymptomMatches", "articles", "symptomText", "setSearchQuery", "query", "setSelectedCategory", "category", "toggleFavorite", "article", "AndroidViewModelFactory", "app_debug"})
public final class ArticleViewModel extends androidx.lifecycle.AndroidViewModel {
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy database$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy repository$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.helloapp.service.ArticleFetcher articleFetcher = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _searchQuery = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> searchQuery = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _selectedCategory = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> selectedCategory = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy allArticles$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy favoriteArticles$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy allCategories$delegate = null;
    
    /**
     * Suggested search when no results (spelling / "did you mean").
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy suggestedSearchQuery$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy filteredArticles$delegate = null;
    
    /**
     * Symptom keywords per condition. Each pair: (symptom keywords, title substrings to match article).
     * Title substrings can be EN or FR so we match article.title.contains(any).
     */
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<kotlin.Pair<java.util.List<java.lang.String>, java.util.List<java.lang.String>>> symptomMap = null;
    
    public ArticleViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    private final com.example.helloapp.data.AppDatabase getDatabase() {
        return null;
    }
    
    private final com.example.helloapp.repository.ArticleRepository getRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getSearchQuery() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getSelectedCategory() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.helloapp.data.Article>> getAllArticles() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.helloapp.data.Article>> getFavoriteArticles() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<java.lang.String>> getAllCategories() {
        return null;
    }
    
    /**
     * Suggested search when no results (spelling / "did you mean").
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getSuggestedSearchQuery() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.helloapp.data.Article>> getFilteredArticles() {
        return null;
    }
    
    public final void setSearchQuery(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    /**
     * Match articles by symptom keywords; returns list sorted by relevance (most matches first).
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.helloapp.data.Article> getSymptomMatches(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.helloapp.data.Article> articles, @org.jetbrains.annotations.NotNull()
    java.lang.String symptomText) {
        return null;
    }
    
    public final void setSelectedCategory(@org.jetbrains.annotations.Nullable()
    java.lang.String category) {
    }
    
    public final void toggleFavorite(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.Article article) {
    }
    
    public final void fetchAndSaveArticles() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job getArticleById(long id) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J%\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00060\tH\u0016\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/example/helloapp/viewmodel/ArticleViewModel$AndroidViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "Companion", "app_debug"})
    public static final class AndroidViewModelFactory implements androidx.lifecycle.ViewModelProvider.Factory {
        @org.jetbrains.annotations.NotNull()
        private final android.app.Application application = null;
        @kotlin.jvm.Volatile()
        @org.jetbrains.annotations.Nullable()
        private static volatile com.example.helloapp.viewmodel.ArticleViewModel.AndroidViewModelFactory INSTANCE;
        @org.jetbrains.annotations.NotNull()
        public static final com.example.helloapp.viewmodel.ArticleViewModel.AndroidViewModelFactory.Companion Companion = null;
        
        public AndroidViewModelFactory(@org.jetbrains.annotations.NotNull()
        android.app.Application application) {
            super();
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public <T extends androidx.lifecycle.ViewModel>T create(@org.jetbrains.annotations.NotNull()
        java.lang.Class<T> modelClass) {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/example/helloapp/viewmodel/ArticleViewModel$AndroidViewModelFactory$Companion;", "", "()V", "INSTANCE", "Lcom/example/helloapp/viewmodel/ArticleViewModel$AndroidViewModelFactory;", "getInstance", "application", "Landroid/app/Application;", "app_debug"})
        public static final class Companion {
            
            private Companion() {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.example.helloapp.viewmodel.ArticleViewModel.AndroidViewModelFactory getInstance(@org.jetbrains.annotations.NotNull()
            android.app.Application application) {
                return null;
            }
        }
    }
}