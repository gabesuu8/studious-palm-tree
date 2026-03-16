package com.example.helloapp.adapter;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u001cB\u0019\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u00a2\u0006\u0002\u0010\u0007J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u001c\u0010\u0011\u001a\u00020\u00062\n\u0010\u0012\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0010H\u0016J\u001c\u0010\u0014\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0010H\u0016J\u001a\u0010\u0018\u001a\u00020\u00062\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fJ\u0014\u0010\u001a\u001a\u00020\u00062\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\n0\tR\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/example/helloapp/adapter/SymptomResultAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/helloapp/adapter/SymptomResultAdapter$ViewHolder;", "onReadArticle", "Lkotlin/Function1;", "Lcom/example/helloapp/data/Article;", "", "(Lkotlin/jvm/functions/Function1;)V", "results", "", "Lcom/example/helloapp/viewmodel/ArticleViewModel$SymptomMatchResult;", "symptomColorMap", "", "", "Lcom/example/helloapp/adapter/SymptomChipColors;", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setSymptomColorMap", "map", "submitList", "newResults", "ViewHolder", "app_debug"})
public final class SymptomResultAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.helloapp.adapter.SymptomResultAdapter.ViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.example.helloapp.data.Article, kotlin.Unit> onReadArticle = null;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.example.helloapp.viewmodel.ArticleViewModel.SymptomMatchResult> results;
    @org.jetbrains.annotations.NotNull()
    private java.util.Map<java.lang.String, com.example.helloapp.adapter.SymptomChipColors> symptomColorMap;
    
    public SymptomResultAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.example.helloapp.data.Article, kotlin.Unit> onReadArticle) {
        super();
    }
    
    public final void setSymptomColorMap(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, com.example.helloapp.adapter.SymptomChipColors> map) {
    }
    
    public final void submitList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.helloapp.viewmodel.ArticleViewModel.SymptomMatchResult> newResults) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.example.helloapp.adapter.SymptomResultAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.adapter.SymptomResultAdapter.ViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/example/helloapp/adapter/SymptomResultAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/example/helloapp/adapter/SymptomResultAdapter;Landroid/view/View;)V", "conditionTitle", "Landroid/widget/TextView;", "confidenceChip", "Lcom/google/android/material/chip/Chip;", "differentiatingChips", "Lcom/google/android/material/chip/ChipGroup;", "differentiatingTipLayout", "matchedSymptomsChips", "readArticleButton", "Lcom/google/android/material/button/MaterialButton;", "urgencyDot", "urgencyLabel", "bind", "", "result", "Lcom/example/helloapp/viewmodel/ArticleViewModel$SymptomMatchResult;", "app_debug"})
    public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView urgencyLabel = null;
        @org.jetbrains.annotations.NotNull()
        private final android.view.View urgencyDot = null;
        @org.jetbrains.annotations.NotNull()
        private final com.google.android.material.chip.Chip confidenceChip = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView conditionTitle = null;
        @org.jetbrains.annotations.NotNull()
        private final com.google.android.material.chip.ChipGroup matchedSymptomsChips = null;
        @org.jetbrains.annotations.NotNull()
        private final android.view.View differentiatingTipLayout = null;
        @org.jetbrains.annotations.NotNull()
        private final com.google.android.material.chip.ChipGroup differentiatingChips = null;
        @org.jetbrains.annotations.NotNull()
        private final com.google.android.material.button.MaterialButton readArticleButton = null;
        
        public ViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View itemView) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.example.helloapp.viewmodel.ArticleViewModel.SymptomMatchResult result) {
        }
    }
}