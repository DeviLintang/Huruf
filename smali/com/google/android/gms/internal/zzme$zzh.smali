.class Lcom/google/android/gms/internal/zzme$zzh;
.super Lcom/google/android/gms/internal/zzme$zzi;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/google/android/gms/internal/zzme;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "zzh"
.end annotation


# instance fields
.field final synthetic zzafW:Lcom/google/android/gms/internal/zzme;

.field private final zzagg:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList",
            "<",
            "Lcom/google/android/gms/common/api/Api$zzb;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>(Lcom/google/android/gms/internal/zzme;Ljava/util/ArrayList;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList",
            "<",
            "Lcom/google/android/gms/common/api/Api$zzb;",
            ">;)V"
        }
    .end annotation

    iput-object p1, p0, Lcom/google/android/gms/internal/zzme$zzh;->zzafW:Lcom/google/android/gms/internal/zzme;

    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lcom/google/android/gms/internal/zzme$zzi;-><init>(Lcom/google/android/gms/internal/zzme;Lcom/google/android/gms/internal/zzme$1;)V

    iput-object p2, p0, Lcom/google/android/gms/internal/zzme$zzh;->zzagg:Ljava/util/ArrayList;

    return-void
.end method


# virtual methods
.method public zzpc()V
    .locals 3

    iget-object v0, p0, Lcom/google/android/gms/internal/zzme$zzh;->zzagg:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/google/android/gms/common/api/Api$zzb;

    iget-object v2, p0, Lcom/google/android/gms/internal/zzme$zzh;->zzafW:Lcom/google/android/gms/internal/zzme;

    invoke-static {v2}, Lcom/google/android/gms/internal/zzme;->zzg(Lcom/google/android/gms/internal/zzme;)Lcom/google/android/gms/common/internal/zzp;

    move-result-object v2

    invoke-interface {v0, v2}, Lcom/google/android/gms/common/api/Api$zzb;->zza(Lcom/google/android/gms/common/internal/zzp;)V

    goto :goto_0

    :cond_0
    return-void
.end method
