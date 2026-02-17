package com.example.helloapp;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0014J\u0012\u0010\u0015\u001a\u00020\u00122\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0012H\u0002J\u0010\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0016\u0010\u001e\u001a\u00020\u00122\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001b0 H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/example/helloapp/VaccinationActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/example/helloapp/adapter/VaccinationAdapter;", "chipAll", "Lcom/google/android/material/chip/Chip;", "chipCompleted", "emptyStateText", "Landroid/widget/TextView;", "progressBar", "Landroid/widget/ProgressBar;", "progressCount", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "viewModel", "Lcom/example/helloapp/viewmodel/VaccinationViewModel;", "attachBaseContext", "", "newBase", "Landroid/content/Context;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupFilterChips", "showDatePicker", "vaccination", "Lcom/example/helloapp/data/Vaccination;", "showVaccinationDetails", "toggleVaccination", "updateProgress", "vaccinations", "", "app_debug"})
public final class VaccinationActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.helloapp.viewmodel.VaccinationViewModel viewModel;
    private androidx.recyclerview.widget.RecyclerView recyclerView;
    private android.widget.TextView emptyStateText;
    private android.widget.TextView progressCount;
    private android.widget.ProgressBar progressBar;
    private com.example.helloapp.adapter.VaccinationAdapter adapter;
    private com.google.android.material.chip.Chip chipAll;
    private com.google.android.material.chip.Chip chipCompleted;
    
    public VaccinationActivity() {
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
    
    private final void setupFilterChips() {
    }
    
    private final void toggleVaccination(com.example.helloapp.data.Vaccination vaccination) {
    }
    
    private final void showVaccinationDetails(com.example.helloapp.data.Vaccination vaccination) {
    }
    
    private final void showDatePicker(com.example.helloapp.data.Vaccination vaccination) {
    }
    
    private final void updateProgress(java.util.List<com.example.helloapp.data.Vaccination> vaccinations) {
    }
}