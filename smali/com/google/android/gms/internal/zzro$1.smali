.class Lcom/google/android/gms/internal/zzro$1;
.super Lcom/google/android/gms/internal/zzro$zza;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/google/android/gms/internal/zzro;->loadVisible(Lcom/google/android/gms/common/api/GoogleApiClient;ILjava/lang/String;)Lcom/google/android/gms/common/api/PendingResult;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic zzaZn:Ljava/lang/String;

.field final synthetic zzaZu:I

.field final synthetic zzaZv:Lcom/google/android/gms/internal/zzro;


# direct methods
.method constructor <init>(Lcom/google/android/gms/internal/zzro;Lcom/google/android/gms/common/api/GoogleApiClient;ILjava/lang/String;)V
    .locals 1

    iput-object p1, p0, Lcom/google/android/gms/internal/zzro$1;->zzaZv:Lcom/google/android/gms/internal/zzro;

    iput p3, p0, Lcom/google/android/gms/internal/zzro$1;->zzaZu:I

    iput-object p4, p0, Lcom/google/android/gms/internal/zzro$1;->zzaZn:Ljava/lang/String;

    const/4 v0, 0x0

    invoke-direct {p0, p2, v0}, Lcom/google/android/gms/internal/zzro$zza;-><init>(Lcom/google/android/gms/common/api/GoogleApiClient;Lcom/google/android/gms/internal/zzro$1;)V

    return-void
.end method


# virtual methods
.method protected bridge synthetic zza(Lcom/google/android/gms/common/api/Api$zzb;)V
    .locals 0
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Landroid/os/RemoteException;
        }
    .end annotation

    check-cast p1, Lcom/google/android/gms/plus/internal/zze;

    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/zzro$1;->zza(Lcom/google/android/gms/plus/internal/zze;)V

    return-void
.end method

.method protected zza(Lcom/google/android/gms/plus/internal/zze;)V
    .locals 2

    iget v0, p0, Lcom/google/android/gms/internal/zzro$1;->zzaZu:I

    iget-object v1, p0, Lcom/google/android/gms/internal/zzro$1;->zzaZn:Ljava/lang/String;

    invoke-virtual {p1, p0, v0, v1}, Lcom/google/android/gms/plus/internal/zze;->zza(Lcom/google/android/gms/internal/zzlx$zzb;ILjava/lang/String;)Lcom/google/android/gms/common/internal/zzq;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/google/android/gms/internal/zzro$1;->zza(Lcom/google/android/gms/common/internal/zzq;)V

    return-void
.end method
