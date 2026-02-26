package com.example.helloapp.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\tH\u0002J&\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000eJ8\u0010\u0011\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00042\u0018\u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000e0\u00160\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/example/helloapp/util/NotificationHelper;", "", "()V", "CHANNEL_PRENATAL", "", "CHANNEL_VACCINATION", "createChannels", "", "context", "Landroid/content/Context;", "mainActivityIntent", "Landroid/app/PendingIntent;", "postPrenatalNotification", "pregnancyId", "", "motherName", "daysUntilVisit", "postVaccinationNotification", "childId", "childName", "vaccines", "", "Lkotlin/Pair;", "app_debug"})
public final class NotificationHelper {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHANNEL_VACCINATION = "vaccination_reminders";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHANNEL_PRENATAL = "prenatal_reminders";
    @org.jetbrains.annotations.NotNull()
    public static final com.example.helloapp.util.NotificationHelper INSTANCE = null;
    
    private NotificationHelper() {
        super();
    }
    
    public final void createChannels(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    /**
     * [vaccines] is a list of (vaccineName, daysUntilDue) pairs, sorted by urgency.
     * Negative daysUntilDue = overdue.
     */
    public final void postVaccinationNotification(@org.jetbrains.annotations.NotNull()
    android.content.Context context, long childId, @org.jetbrains.annotations.NotNull()
    java.lang.String childName, @org.jetbrains.annotations.NotNull()
    java.util.List<kotlin.Pair<java.lang.String, java.lang.Long>> vaccines) {
    }
    
    public final void postPrenatalNotification(@org.jetbrains.annotations.NotNull()
    android.content.Context context, long pregnancyId, @org.jetbrains.annotations.NotNull()
    java.lang.String motherName, long daysUntilVisit) {
    }
    
    private final android.app.PendingIntent mainActivityIntent(android.content.Context context) {
        return null;
    }
}