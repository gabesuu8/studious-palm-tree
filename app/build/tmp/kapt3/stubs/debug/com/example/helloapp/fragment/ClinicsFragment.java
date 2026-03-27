package com.example.helloapp.fragment;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010%\u001a\u00020&H\u0002J\b\u0010\'\u001a\u00020&H\u0003J\b\u0010(\u001a\u00020\u0016H\u0002J\b\u0010)\u001a\u00020\u0016H\u0002J\b\u0010*\u001a\u00020&H\u0002J&\u0010+\u001a\u0004\u0018\u00010\u000e2\u0006\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/2\b\u00100\u001a\u0004\u0018\u000101H\u0016J\b\u00102\u001a\u00020&H\u0016J\u0010\u00103\u001a\u00020&2\u0006\u00104\u001a\u00020\u0014H\u0016J\u001a\u00105\u001a\u00020&2\u0006\u00106\u001a\u00020\u000e2\b\u00100\u001a\u0004\u0018\u000101H\u0016J\b\u00107\u001a\u00020&H\u0002J\u0016\u00108\u001a\u00020&2\f\u00109\u001a\b\u0012\u0004\u0012\u00020\u001a0\u000bH\u0002J\b\u0010:\u001a\u00020&H\u0002J\b\u0010;\u001a\u00020&H\u0002J\u0010\u0010<\u001a\u00020&2\u0006\u0010=\u001a\u00020\fH\u0002J\b\u0010>\u001a\u00020&H\u0002J\b\u0010?\u001a\u00020&H\u0002J\b\u0010@\u001a\u00020&H\u0002J\u001c\u0010A\u001a\u00020&2\u0006\u0010B\u001a\u00020\u00162\n\b\u0002\u0010C\u001a\u0004\u0018\u00010DH\u0002J\b\u0010E\u001a\u00020&H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00190\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006F"}, d2 = {"Lcom/example/helloapp/fragment/ClinicsFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/google/android/gms/maps/OnMapReadyCallback;", "()V", "adapter", "Lcom/example/helloapp/adapter/ClinicAdapter;", "btnEnableLocation", "Landroid/widget/Button;", "chipEmergency", "Lcom/google/android/material/chip/Chip;", "currentClinics", "", "Lcom/example/helloapp/viewmodel/ClinicWithDistance;", "emptyState", "Landroid/view/View;", "fabToggleView", "Lcom/google/android/material/floatingactionbutton/FloatingActionButton;", "fusedLocationClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "googleMap", "Lcom/google/android/gms/maps/GoogleMap;", "isMapView", "", "locationPermissionRequest", "Landroidx/activity/result/ActivityResultLauncher;", "", "", "locationStatusBar", "mapContainer", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "spinnerCountry", "Landroid/widget/Spinner;", "txtLocationStatus", "Landroid/widget/TextView;", "viewModel", "Lcom/example/helloapp/viewmodel/ClinicViewModel;", "checkLocationPermission", "", "getCurrentLocation", "hasLocationPermission", "isNetworkAvailable", "observeData", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onMapReady", "map", "onViewCreated", "view", "requestLocationPermission", "setupCountrySpinner", "countries", "setupListeners", "setupMap", "showClinicDetails", "clinicWithDistance", "showLanguageDialog", "showOptionsDialog", "toggleView", "updateLocationUI", "hasLocation", "location", "Landroid/location/Location;", "updateMapMarkers", "app_debug"})
public final class ClinicsFragment extends androidx.fragment.app.Fragment implements com.google.android.gms.maps.OnMapReadyCallback {
    @org.jetbrains.annotations.Nullable()
    private com.example.helloapp.viewmodel.ClinicViewModel viewModel;
    @org.jetbrains.annotations.Nullable()
    private com.google.android.gms.location.FusedLocationProviderClient fusedLocationClient;
    @org.jetbrains.annotations.Nullable()
    private androidx.recyclerview.widget.RecyclerView recyclerView;
    @org.jetbrains.annotations.Nullable()
    private android.view.View emptyState;
    @org.jetbrains.annotations.Nullable()
    private android.view.View mapContainer;
    @org.jetbrains.annotations.Nullable()
    private android.view.View locationStatusBar;
    @org.jetbrains.annotations.Nullable()
    private android.widget.TextView txtLocationStatus;
    @org.jetbrains.annotations.Nullable()
    private android.widget.Button btnEnableLocation;
    @org.jetbrains.annotations.Nullable()
    private com.google.android.material.chip.Chip chipEmergency;
    @org.jetbrains.annotations.Nullable()
    private android.widget.Spinner spinnerCountry;
    @org.jetbrains.annotations.Nullable()
    private com.google.android.material.floatingactionbutton.FloatingActionButton fabToggleView;
    @org.jetbrains.annotations.Nullable()
    private com.example.helloapp.adapter.ClinicAdapter adapter;
    @org.jetbrains.annotations.Nullable()
    private com.google.android.gms.maps.GoogleMap googleMap;
    private boolean isMapView = false;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.example.helloapp.viewmodel.ClinicWithDistance> currentClinics;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String[]> locationPermissionRequest = null;
    
    public ClinicsFragment() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupListeners() {
    }
    
    private final void setupMap() {
    }
    
    @java.lang.Override()
    public void onMapReady(@org.jetbrains.annotations.NotNull()
    com.google.android.gms.maps.GoogleMap map) {
    }
    
    private final void observeData() {
    }
    
    private final void setupCountrySpinner(java.util.List<java.lang.String> countries) {
    }
    
    private final void toggleView() {
    }
    
    private final void updateMapMarkers() {
    }
    
    private final void checkLocationPermission() {
    }
    
    private final boolean hasLocationPermission() {
        return false;
    }
    
    private final void requestLocationPermission() {
    }
    
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    private final void getCurrentLocation() {
    }
    
    private final void updateLocationUI(boolean hasLocation, android.location.Location location) {
    }
    
    private final boolean isNetworkAvailable() {
        return false;
    }
    
    private final void showClinicDetails(com.example.helloapp.viewmodel.ClinicWithDistance clinicWithDistance) {
    }
    
    private final void showOptionsDialog() {
    }
    
    private final void showLanguageDialog() {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}