.class Lcom/google/android/gms/internal/zzrt$1;
.super Lcom/google/android/gms/internal/zzrt$zzb;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/google/android/gms/internal/zzrt;->attest(Lcom/google/android/gms/common/api/GoogleApiClient;[B)Lcom/google/android/gms/common/api/PendingResult;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic zzbbk:[B

.field final synthetic zzbbl:Lcom/google/android/gms/internal/zzrt;


# direct methods
.method constructor <init>(Lcom/google/android/gms/internal/zzrt;Lcom/google/android/gms/common/api/GoogleApiClient;[B)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/gms/internal/zzrt$1;->zzbbl:Lcom/google/android/gms/internal/zzrt;

    iput-object p3, p0, Lcom/google/android/gms/internal/zzrt$1;->zzbbk:[B

    invoke-direct {p0, p2}, Lcom/google/android/gms/internal/zzrt$zzb;-><init>(Lcom/google/android/gms/common/api/GoogleApiClient;)V

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

    check-cast p1, Lcom/google/android/gms/internal/zzru;

    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/zzrt$1;->zza(Lcom/google/android/gms/internal/zzru;)V

    return-void
.end method

.method protected zza(Lcom/google/android/gms/internal/zzru;)V
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Landroid/os/RemoteException;
        }
    .end annotation

    iget-object v0, p0, Lcom/google/android/gms/internal/zzrt$1;->zzbbp:Lcom/google/android/gms/internal/zzrr;

    iget-object v1, p0, Lcom/google/android/gms/internal/zzrt$1;->zzbbk:[B

    invoke-virtual {p1, v0, v1}, Lcom/google/android/gms/internal/zzru;->zza(Lcom/google/android/gms/internal/zzrr;[B)V

    return-void
.end method
