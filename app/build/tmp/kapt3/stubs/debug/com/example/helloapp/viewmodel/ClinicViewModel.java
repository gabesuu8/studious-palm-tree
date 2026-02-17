package com.example.helloapp.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\n\u0018\u0000 12\u00020\u0001:\u000212B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J(\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020#2\u0006\u0010&\u001a\u00020#2\u0006\u0010\'\u001a\u00020#H\u0002J\u000e\u0010(\u001a\u00020)H\u0082@\u00a2\u0006\u0002\u0010*J\u000e\u0010+\u001a\u00020)2\u0006\u0010,\u001a\u00020\u0007J\u0010\u0010-\u001a\u00020)2\b\u0010.\u001a\u0004\u0018\u00010\tJ\u0010\u0010/\u001a\u00020)2\b\u00100\u001a\u0004\u0018\u00010\u000bR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001d\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000e0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u000e\u0010\u0014\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00070\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001d\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u000e0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0011R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u001e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018R\u0019\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0018\u00a8\u00063"}, d2 = {"Lcom/example/helloapp/viewmodel/ClinicViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_filterEmergencyOnly", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_selectedCountry", "", "_userLocation", "Landroid/location/Location;", "allClinics", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/helloapp/data/Clinic;", "getAllClinics", "()Lkotlinx/coroutines/flow/Flow;", "allCountries", "getAllCountries", "defaultLomeLocation", "filterEmergencyOnly", "Lkotlinx/coroutines/flow/StateFlow;", "getFilterEmergencyOnly", "()Lkotlinx/coroutines/flow/StateFlow;", "filteredClinics", "Lcom/example/helloapp/viewmodel/ClinicWithDistance;", "getFilteredClinics", "repository", "Lcom/example/helloapp/repository/ClinicRepository;", "selectedCountry", "getSelectedCountry", "userLocation", "getUserLocation", "calculateDistance", "", "lat1", "lon1", "lat2", "lon2", "initializeSampleClinics", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setEmergencyFilter", "emergencyOnly", "setSelectedCountry", "country", "setUserLocation", "location", "Companion", "Factory", "app_debug"})
public final class ClinicViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.helloapp.repository.ClinicRepository repository = null;
    
    /**
     * Default center: B.P. 1413, Lomé, Togo (city center)
     */
    @org.jetbrains.annotations.NotNull()
    private final android.location.Location defaultLomeLocation = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<android.location.Location> _userLocation = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<android.location.Location> userLocation = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _selectedCountry = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> selectedCountry = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _filterEmergencyOnly = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> filterEmergencyOnly = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.helloapp.data.Clinic>> allClinics = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<java.lang.String>> allCountries = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.helloapp.viewmodel.ClinicWithDistance>> filteredClinics = null;
    
    /**
     * Lomé, Togo - B.P. 1413 area (city center)
     */
    public static final double DEFAULT_LOME_LAT = 6.1256;
    public static final double DEFAULT_LOME_LON = 1.2254;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.helloapp.viewmodel.ClinicViewModel.Companion Companion = null;
    
    public ClinicViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<android.location.Location> getUserLocation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getSelectedCountry() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getFilterEmergencyOnly() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.helloapp.data.Clinic>> getAllClinics() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<java.lang.String>> getAllCountries() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.helloapp.viewmodel.ClinicWithDistance>> getFilteredClinics() {
        return null;
    }
    
    public final void setUserLocation(@org.jetbrains.annotations.Nullable()
    android.location.Location location) {
    }
    
    public final void setSelectedCountry(@org.jetbrains.annotations.Nullable()
    java.lang.String country) {
    }
    
    public final void setEmergencyFilter(boolean emergencyOnly) {
    }
    
    private final double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        return 0.0;
    }
    
    private final java.lang.Object initializeSampleClinics(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/example/helloapp/viewmodel/ClinicViewModel$Companion;", "", "()V", "DEFAULT_LOME_LAT", "", "DEFAULT_LOME_LON", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J%\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00060\tH\u0016\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/example/helloapp/viewmodel/ClinicViewModel$Factory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "app_debug"})
    public static final class Factory implements androidx.lifecycle.ViewModelProvider.Factory {
        @org.jetbrains.annotations.NotNull()
        private final android.app.Application application = null;
        
        public Factory(@org.jetbrains.annotations.NotNull()
        android.app.Application application) {
            super();
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public <T extends androidx.lifecycle.ViewModel>T create(@org.jetbrains.annotations.NotNull()
        java.lang.Class<T> modelClass) {
            return null;
        }
    }
}