package com.example.helloapp;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0014J\b\u0010 \u001a\u00020\u001dH\u0002J\b\u0010!\u001a\u00020\u001dH\u0002J\u0012\u0010\"\u001a\u00020\u001d2\b\u0010#\u001a\u0004\u0018\u00010$H\u0014J\b\u0010%\u001a\u00020\u001dH\u0002J\b\u0010&\u001a\u00020\u001dH\u0002J\u0018\u0010'\u001a\u00020\u001d2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\fH\u0002J\u0016\u0010+\u001a\u00020\u001d2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002J\u0018\u0010,\u001a\u00020\u001d2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\fH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0014X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0014X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0014X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0014X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006-"}, d2 = {"Lcom/example/helloapp/GrowthTrackerActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/example/helloapp/adapter/GrowthRecordAdapter;", "btnAddChild", "Landroid/widget/Button;", "btnAddMeasurement", "childSpinner", "Landroid/widget/Spinner;", "children", "", "Lcom/example/helloapp/data/Child;", "emptyState", "Landroid/view/View;", "historyCard", "recordsRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "statusCard", "txtBmi", "Landroid/widget/TextView;", "txtHeight", "txtHeightZScore", "txtNutritionalStatus", "txtWeight", "txtWeightZScore", "viewModel", "Lcom/example/helloapp/viewmodel/GrowthViewModel;", "attachBaseContext", "", "newBase", "Landroid/content/Context;", "clearStatusDisplay", "observeData", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "showAddChildDialog", "showAddMeasurementDialog", "showRecordDetails", "record", "Lcom/example/helloapp/data/GrowthRecord;", "child", "updateChildSpinner", "updateStatusDisplay", "app_debug"})
public final class GrowthTrackerActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.helloapp.viewmodel.GrowthViewModel viewModel;
    private android.widget.Spinner childSpinner;
    private android.widget.Button btnAddChild;
    private android.view.View statusCard;
    private android.view.View historyCard;
    private android.view.View emptyState;
    private android.widget.TextView txtWeight;
    private android.widget.TextView txtHeight;
    private android.widget.TextView txtBmi;
    private android.widget.TextView txtNutritionalStatus;
    private android.widget.TextView txtWeightZScore;
    private android.widget.TextView txtHeightZScore;
    private android.widget.Button btnAddMeasurement;
    private androidx.recyclerview.widget.RecyclerView recordsRecyclerView;
    @org.jetbrains.annotations.Nullable()
    private com.example.helloapp.adapter.GrowthRecordAdapter adapter;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.example.helloapp.data.Child> children;
    
    public GrowthTrackerActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void attachBaseContext(@org.jetbrains.annotations.NotNull()
    android.content.Context newBase) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void observeData() {
    }
    
    private final void updateChildSpinner(java.util.List<com.example.helloapp.data.Child> children) {
    }
    
    private final void updateStatusDisplay(com.example.helloapp.data.GrowthRecord record, com.example.helloapp.data.Child child) {
    }
    
    private final void clearStatusDisplay() {
    }
    
    private final void showAddChildDialog() {
    }
    
    private final void showAddMeasurementDialog() {
    }
    
    private final void showRecordDetails(com.example.helloapp.data.GrowthRecord record, com.example.helloapp.data.Child child) {
    }
}