package com.example.helloapp.adapter;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u0012\u0013B!\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\u0002\u0010\tJ\u0018\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\rH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/example/helloapp/adapter/GrowthRecordAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/example/helloapp/data/GrowthRecord;", "Lcom/example/helloapp/adapter/GrowthRecordAdapter$GrowthRecordViewHolder;", "child", "Lcom/example/helloapp/data/Child;", "onItemClick", "Lkotlin/Function1;", "", "(Lcom/example/helloapp/data/Child;Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "GrowthRecordDiffCallback", "GrowthRecordViewHolder", "app_debug"})
public final class GrowthRecordAdapter extends androidx.recyclerview.widget.ListAdapter<com.example.helloapp.data.GrowthRecord, com.example.helloapp.adapter.GrowthRecordAdapter.GrowthRecordViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final com.example.helloapp.data.Child child = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.example.helloapp.data.GrowthRecord, kotlin.Unit> onItemClick = null;
    
    public GrowthRecordAdapter(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.Child child, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.example.helloapp.data.GrowthRecord, kotlin.Unit> onItemClick) {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.example.helloapp.adapter.GrowthRecordAdapter.GrowthRecordViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.adapter.GrowthRecordAdapter.GrowthRecordViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/example/helloapp/adapter/GrowthRecordAdapter$GrowthRecordDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/example/helloapp/data/GrowthRecord;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"})
    public static final class GrowthRecordDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.example.helloapp.data.GrowthRecord> {
        
        public GrowthRecordDiffCallback() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        com.example.helloapp.data.GrowthRecord oldItem, @org.jetbrains.annotations.NotNull()
        com.example.helloapp.data.GrowthRecord newItem) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        com.example.helloapp.data.GrowthRecord oldItem, @org.jetbrains.annotations.NotNull()
        com.example.helloapp.data.GrowthRecord newItem) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J*\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\f0\u0012R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/example/helloapp/adapter/GrowthRecordAdapter$GrowthRecordViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "txtAge", "Landroid/widget/TextView;", "txtDate", "txtRecordHeight", "txtRecordStatus", "txtRecordWeight", "bind", "", "record", "Lcom/example/helloapp/data/GrowthRecord;", "child", "Lcom/example/helloapp/data/Child;", "onItemClick", "Lkotlin/Function1;", "app_debug"})
    public static final class GrowthRecordViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView txtDate = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView txtAge = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView txtRecordWeight = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView txtRecordHeight = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView txtRecordStatus = null;
        
        public GrowthRecordViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View itemView) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.example.helloapp.data.GrowthRecord record, @org.jetbrains.annotations.NotNull()
        com.example.helloapp.data.Child child, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super com.example.helloapp.data.GrowthRecord, kotlin.Unit> onItemClick) {
        }
    }
}