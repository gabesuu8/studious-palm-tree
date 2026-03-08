package com.example.helloapp.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0011\u0018\u0000 Z2\u00020\u0001:\u0006YZ[\\]^B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00070@2\f\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bH\u0002J\u0006\u0010B\u001a\u00020CJ\u000e\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020GJ\"\u0010H\u001a\b\u0012\u0004\u0012\u00020I0\u000b2\f\u0010J\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010K\u001a\u00020\u0007J$\u0010L\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010J\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010K\u001a\u00020\u0007H\u0007J,\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020\u00072\f\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b2\f\u0010P\u001a\b\u0012\u0004\u0012\u00020\u00070@H\u0002J\u000e\u0010Q\u001a\u00020C2\u0006\u0010R\u001a\u00020\u0007J\u0010\u0010S\u001a\u00020C2\b\u0010T\u001a\u0004\u0018\u00010\u0007J\u000e\u0010U\u001a\u00020C2\u0006\u0010V\u001a\u00020\fJ\u0016\u0010W\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b2\u0006\u0010X\u001a\u00020\u0007H\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\'\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\'\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b0\n8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0012\u0010\u000eR\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0018\u001a\n \u001a*\u0004\u0018\u00010\u00190\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u001b\u001a\u00020\u001c8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001f\u0010\u0010\u001a\u0004\b\u001d\u0010\u001eR\'\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\"\u0010\u0010\u001a\u0004\b!\u0010\u000eR\'\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b%\u0010\u0010\u001a\u0004\b$\u0010\u000eR \u0010&\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b0\'X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010(\u001a\u00020)8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b,\u0010\u0010\u001a\u0004\b*\u0010+R\u0017\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00070.\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0019\u00101\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070.\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u00100R#\u00103\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\n8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b5\u0010\u0010\u001a\u0004\b4\u0010\u000eR8\u00106\u001a \u0012\u001c\u0012\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b070\u000b8BX\u0082\u0004\u00a2\u0006\f\u0012\u0004\b8\u00109\u001a\u0004\b:\u0010;R!\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b>\u0010\u0010\u001a\u0004\b=\u0010;\u00a8\u0006_"}, d2 = {"Lcom/example/helloapp/viewmodel/ArticleViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_searchQuery", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_selectedCategory", "allArticles", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/helloapp/data/Article;", "getAllArticles", "()Lkotlinx/coroutines/flow/Flow;", "allArticles$delegate", "Lkotlin/Lazy;", "allCategories", "getAllCategories", "allCategories$delegate", "articleFetcher", "Lcom/example/helloapp/service/ArticleFetcher;", "conditionProfiles", "Lcom/example/helloapp/viewmodel/ArticleViewModel$ConditionProfile;", "context", "Landroid/content/Context;", "kotlin.jvm.PlatformType", "database", "Lcom/example/helloapp/data/AppDatabase;", "getDatabase", "()Lcom/example/helloapp/data/AppDatabase;", "database$delegate", "favoriteArticles", "getFavoriteArticles", "favoriteArticles$delegate", "filteredArticles", "getFilteredArticles", "filteredArticles$delegate", "informalSymptomMap", "", "repository", "Lcom/example/helloapp/repository/ArticleRepository;", "getRepository", "()Lcom/example/helloapp/repository/ArticleRepository;", "repository$delegate", "searchQuery", "Lkotlinx/coroutines/flow/StateFlow;", "getSearchQuery", "()Lkotlinx/coroutines/flow/StateFlow;", "selectedCategory", "getSelectedCategory", "suggestedSearchQuery", "getSuggestedSearchQuery", "suggestedSearchQuery$delegate", "symptomMap", "Lkotlin/Pair;", "getSymptomMap$annotations", "()V", "getSymptomMap", "()Ljava/util/List;", "symptomPhrases", "getSymptomPhrases", "symptomPhrases$delegate", "expandSymptomTokens", "", "tokens", "fetchAndSaveArticles", "", "getArticleById", "Lkotlinx/coroutines/Job;", "id", "", "getSymptomMatchResults", "Lcom/example/helloapp/viewmodel/ArticleViewModel$SymptomMatchResult;", "articles", "symptomText", "getSymptomMatches", "keywordPresent", "", "keyword", "expandedTerms", "setSearchQuery", "query", "setSelectedCategory", "category", "toggleFavorite", "article", "tokenizeSymptomInput", "input", "AndroidViewModelFactory", "Companion", "ConditionProfile", "ConditionUrgency", "MatchConfidence", "SymptomMatchResult", "app_debug"})
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
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.example.helloapp.viewmodel.ArticleViewModel.ConditionProfile> conditionProfiles = null;
    
    /**
     * Informal/colloquial terms → formal symptom keywords (for expansion).
     */
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.String, java.util.List<java.lang.String>> informalSymptomMap = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy symptomPhrases$delegate = null;
    @java.lang.Deprecated()
    public static final int KEY_SYMPTOM_WEIGHT = 3;
    @java.lang.Deprecated()
    public static final int COMMON_SYMPTOM_WEIGHT = 1;
    @java.lang.Deprecated()
    public static final int MAX_RESULTS = 5;
    @java.lang.Deprecated()
    public static final double HIGH_CONFIDENCE_THRESHOLD = 0.45;
    @java.lang.Deprecated()
    public static final double MODERATE_CONFIDENCE_THRESHOLD = 0.25;
    @org.jetbrains.annotations.NotNull()
    private static final com.example.helloapp.viewmodel.ArticleViewModel.Companion Companion = null;
    
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
    
    @kotlin.Suppress(names = {"unused"})
    @java.lang.Deprecated()
    private static void getSymptomMap$annotations() {
    }
    
    private final java.util.List<kotlin.Pair<java.util.List<java.lang.String>, java.util.List<java.lang.String>>> getSymptomMap() {
        return null;
    }
    
    private final java.util.List<java.lang.String> getSymptomPhrases() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.helloapp.viewmodel.ArticleViewModel.SymptomMatchResult> getSymptomMatchResults(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.helloapp.data.Article> articles, @org.jetbrains.annotations.NotNull()
    java.lang.String symptomText) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Deprecated()
    public final java.util.List<com.example.helloapp.data.Article> getSymptomMatches(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.helloapp.data.Article> articles, @org.jetbrains.annotations.NotNull()
    java.lang.String symptomText) {
        return null;
    }
    
    private final java.util.List<java.lang.String> tokenizeSymptomInput(java.lang.String input) {
        return null;
    }
    
    private final java.util.Set<java.lang.String> expandSymptomTokens(java.util.List<java.lang.String> tokens) {
        return null;
    }
    
    private final boolean keywordPresent(java.lang.String keyword, java.util.List<java.lang.String> tokens, java.util.Set<java.lang.String> expandedTerms) {
        return false;
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/example/helloapp/viewmodel/ArticleViewModel$Companion;", "", "()V", "COMMON_SYMPTOM_WEIGHT", "", "HIGH_CONFIDENCE_THRESHOLD", "", "KEY_SYMPTOM_WEIGHT", "MAX_RESULTS", "MODERATE_CONFIDENCE_THRESHOLD", "app_debug"})
    static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001BC\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\bH\u00c6\u0003J\t\u0010\u0018\u001a\u00020\nH\u00c6\u0003JM\u0010\u0019\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u00c6\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001d\u001a\u00020\bH\u00d6\u0001J\t\u0010\u001e\u001a\u00020\u0004H\u00d6\u0001R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000f\u00a8\u0006\u001f"}, d2 = {"Lcom/example/helloapp/viewmodel/ArticleViewModel$ConditionProfile;", "", "titleSubstrings", "", "", "symptoms", "keySymptoms", "minSymptoms", "", "defaultUrgency", "Lcom/example/helloapp/viewmodel/ArticleViewModel$ConditionUrgency;", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;ILcom/example/helloapp/viewmodel/ArticleViewModel$ConditionUrgency;)V", "getDefaultUrgency", "()Lcom/example/helloapp/viewmodel/ArticleViewModel$ConditionUrgency;", "getKeySymptoms", "()Ljava/util/List;", "getMinSymptoms", "()I", "getSymptoms", "getTitleSubstrings", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
    static final class ConditionProfile {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<java.lang.String> titleSubstrings = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<java.lang.String> symptoms = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<java.lang.String> keySymptoms = null;
        private final int minSymptoms = 0;
        @org.jetbrains.annotations.NotNull()
        private final com.example.helloapp.viewmodel.ArticleViewModel.ConditionUrgency defaultUrgency = null;
        
        public ConditionProfile(@org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> titleSubstrings, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> symptoms, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> keySymptoms, int minSymptoms, @org.jetbrains.annotations.NotNull()
        com.example.helloapp.viewmodel.ArticleViewModel.ConditionUrgency defaultUrgency) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> getTitleSubstrings() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> getSymptoms() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> getKeySymptoms() {
            return null;
        }
        
        public final int getMinSymptoms() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.helloapp.viewmodel.ArticleViewModel.ConditionUrgency getDefaultUrgency() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> component3() {
            return null;
        }
        
        public final int component4() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.helloapp.viewmodel.ArticleViewModel.ConditionUrgency component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.helloapp.viewmodel.ArticleViewModel.ConditionProfile copy(@org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> titleSubstrings, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> symptoms, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> keySymptoms, int minSymptoms, @org.jetbrains.annotations.NotNull()
        com.example.helloapp.viewmodel.ArticleViewModel.ConditionUrgency defaultUrgency) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/example/helloapp/viewmodel/ArticleViewModel$ConditionUrgency;", "", "(Ljava/lang/String;I)V", "EMERGENCY", "SEE_DOCTOR", "HOME_CARE", "app_debug"})
    public static enum ConditionUrgency {
        /*public static final*/ EMERGENCY /* = new EMERGENCY() */,
        /*public static final*/ SEE_DOCTOR /* = new SEE_DOCTOR() */,
        /*public static final*/ HOME_CARE /* = new HOME_CARE() */;
        
        ConditionUrgency() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.example.helloapp.viewmodel.ArticleViewModel.ConditionUrgency> getEntries() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/example/helloapp/viewmodel/ArticleViewModel$MatchConfidence;", "", "(Ljava/lang/String;I)V", "HIGH", "MODERATE", "LOW", "app_debug"})
    public static enum MatchConfidence {
        /*public static final*/ HIGH /* = new HIGH() */,
        /*public static final*/ MODERATE /* = new MODERATE() */,
        /*public static final*/ LOW /* = new LOW() */;
        
        MatchConfidence() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.example.helloapp.viewmodel.ArticleViewModel.MatchConfidence> getEntries() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u00a2\u0006\u0002\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\tH\u00c6\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u00c6\u0003J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u00c6\u0003JQ\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u00c6\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010$\u001a\u00020%H\u00d6\u0001J\t\u0010&\u001a\u00020\fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019\u00a8\u0006\'"}, d2 = {"Lcom/example/helloapp/viewmodel/ArticleViewModel$SymptomMatchResult;", "", "article", "Lcom/example/helloapp/data/Article;", "confidence", "Lcom/example/helloapp/viewmodel/ArticleViewModel$MatchConfidence;", "urgency", "Lcom/example/helloapp/viewmodel/ArticleViewModel$ConditionUrgency;", "score", "", "matchedSymptoms", "", "", "differentiatingSymptoms", "(Lcom/example/helloapp/data/Article;Lcom/example/helloapp/viewmodel/ArticleViewModel$MatchConfidence;Lcom/example/helloapp/viewmodel/ArticleViewModel$ConditionUrgency;DLjava/util/List;Ljava/util/List;)V", "getArticle", "()Lcom/example/helloapp/data/Article;", "getConfidence", "()Lcom/example/helloapp/viewmodel/ArticleViewModel$MatchConfidence;", "getDifferentiatingSymptoms", "()Ljava/util/List;", "getMatchedSymptoms", "getScore", "()D", "getUrgency", "()Lcom/example/helloapp/viewmodel/ArticleViewModel$ConditionUrgency;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
    public static final class SymptomMatchResult {
        @org.jetbrains.annotations.NotNull()
        private final com.example.helloapp.data.Article article = null;
        @org.jetbrains.annotations.NotNull()
        private final com.example.helloapp.viewmodel.ArticleViewModel.MatchConfidence confidence = null;
        @org.jetbrains.annotations.NotNull()
        private final com.example.helloapp.viewmodel.ArticleViewModel.ConditionUrgency urgency = null;
        private final double score = 0.0;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<java.lang.String> matchedSymptoms = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<java.lang.String> differentiatingSymptoms = null;
        
        public SymptomMatchResult(@org.jetbrains.annotations.NotNull()
        com.example.helloapp.data.Article article, @org.jetbrains.annotations.NotNull()
        com.example.helloapp.viewmodel.ArticleViewModel.MatchConfidence confidence, @org.jetbrains.annotations.NotNull()
        com.example.helloapp.viewmodel.ArticleViewModel.ConditionUrgency urgency, double score, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> matchedSymptoms, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> differentiatingSymptoms) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.helloapp.data.Article getArticle() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.helloapp.viewmodel.ArticleViewModel.MatchConfidence getConfidence() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.helloapp.viewmodel.ArticleViewModel.ConditionUrgency getUrgency() {
            return null;
        }
        
        public final double getScore() {
            return 0.0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> getMatchedSymptoms() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> getDifferentiatingSymptoms() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.helloapp.data.Article component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.helloapp.viewmodel.ArticleViewModel.MatchConfidence component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.helloapp.viewmodel.ArticleViewModel.ConditionUrgency component3() {
            return null;
        }
        
        public final double component4() {
            return 0.0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.helloapp.viewmodel.ArticleViewModel.SymptomMatchResult copy(@org.jetbrains.annotations.NotNull()
        com.example.helloapp.data.Article article, @org.jetbrains.annotations.NotNull()
        com.example.helloapp.viewmodel.ArticleViewModel.MatchConfidence confidence, @org.jetbrains.annotations.NotNull()
        com.example.helloapp.viewmodel.ArticleViewModel.ConditionUrgency urgency, double score, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> matchedSymptoms, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> differentiatingSymptoms) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
}