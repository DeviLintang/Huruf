.class public final Lcom/google/android/gms/internal/zzqd$zzc;
.super Lcom/google/android/gms/internal/zzte;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/google/android/gms/internal/zzqd;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "zzc"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/google/android/gms/internal/zzte",
        "<",
        "Lcom/google/android/gms/internal/zzqd$zzc;",
        ">;"
    }
.end annotation


# instance fields
.field public type:Ljava/lang/String;

.field public zzaJG:[Lcom/google/android/gms/internal/zzqd$zzb;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/google/android/gms/internal/zzte;-><init>()V

    invoke-virtual {p0}, Lcom/google/android/gms/internal/zzqd$zzc;->zzxI()Lcom/google/android/gms/internal/zzqd$zzc;

    return-void
.end method


# virtual methods
.method public equals(Ljava/lang/Object;)Z
    .locals 4
    .param p1, "o"    # Ljava/lang/Object;

    .prologue
    const/4 v1, 0x1

    const/4 v0, 0x0

    if-ne p1, p0, :cond_1

    move v0, v1

    .end local p1    # "o":Ljava/lang/Object;
    :cond_0
    :goto_0
    return v0

    .restart local p1    # "o":Ljava/lang/Object;
    :cond_1
    instance-of v2, p1, Lcom/google/android/gms/internal/zzqd$zzc;

    if-eqz v2, :cond_0

    check-cast p1, Lcom/google/android/gms/internal/zzqd$zzc;

    .end local p1    # "o":Ljava/lang/Object;
    iget-object v2, p0, Lcom/google/android/gms/internal/zzqd$zzc;->type:Ljava/lang/String;

    if-nez v2, :cond_5

    iget-object v2, p1, Lcom/google/android/gms/internal/zzqd$zzc;->type:Ljava/lang/String;

    if-nez v2, :cond_0

    :cond_2
    iget-object v2, p0, Lcom/google/android/gms/internal/zzqd$zzc;->zzaJG:[Lcom/google/android/gms/internal/zzqd$zzb;

    iget-object v3, p1, Lcom/google/android/gms/internal/zzqd$zzc;->zzaJG:[Lcom/google/android/gms/internal/zzqd$zzb;

    invoke-static {v2, v3}, Lcom/google/android/gms/internal/zzti;->equals([Ljava/lang/Object;[Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_0

    iget-object v2, p0, Lcom/google/android/gms/internal/zzqd$zzc;->zzbpQ:Lcom/google/android/gms/internal/zztg;

    if-eqz v2, :cond_3

    iget-object v2, p0, Lcom/google/android/gms/internal/zzqd$zzc;->zzbpQ:Lcom/google/android/gms/internal/zztg;

    invoke-virtual {v2}, Lcom/google/android/gms/internal/zztg;->isEmpty()Z

    move-result v2

    if-eqz v2, :cond_6

    :cond_3
    iget-object v2, p1, Lcom/google/android/gms/internal/zzqd$zzc;->zzbpQ:Lcom/google/android/gms/internal/zztg;

    if-eqz v2, :cond_4

    iget-object v2, p1, Lcom/google/android/gms/internal/zzqd$zzc;->zzbpQ:Lcom/google/android/gms/internal/zztg;

    invoke-virtual {v2}, Lcom/google/android/gms/internal/zztg;->isEmpty()Z

    move-result v2

    if-eqz v2, :cond_0

    :cond_4
    move v0, v1

    goto :goto_0

    :cond_5
    iget-object v2, p0, Lcom/google/android/gms/internal/zzqd$zzc;->type:Ljava/lang/String;

    iget-object v3, p1, Lcom/google/android/gms/internal/zzqd$zzc;->type:Ljava/lang/String;

    invoke-virtual {v2, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_2

    goto :goto_0

    :cond_6
    iget-object v0, p0, Lcom/google/android/gms/internal/zzqd$zzc;->zzbpQ:Lcom/google/android/gms/internal/zztg;

    iget-object v1, p1, Lcom/google/android/gms/internal/zzqd$zzc;->zzbpQ:Lcom/google/android/gms/internal/zztg;

    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/zztg;->equals(Ljava/lang/Object;)Z

    move-result v0

    goto :goto_0
.end method

.method public hashCode()I
    .locals 3

    const/4 v1, 0x0

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->hashCode()I

    move-result v0

    add-int/lit16 v0, v0, 0x20f

    mul-int/lit8 v2, v0, 0x1f

    iget-object v0, p0, Lcom/google/android/gms/internal/zzqd$zzc;->type:Ljava/lang/String;

    if-nez v0, :cond_1

    move v0, v1

    :goto_0
    add-int/2addr v0, v2

    mul-int/lit8 v0, v0, 0x1f

    iget-object v2, p0, Lcom/google/android/gms/internal/zzqd$zzc;->zzaJG:[Lcom/google/android/gms/internal/zzqd$zzb;

    invoke-static {v2}, Lcom/google/android/gms/internal/zzti;->hashCode([Ljava/lang/Object;)I

    move-result v2

    add-int/2addr v0, v2

    mul-int/lit8 v0, v0, 0x1f

    iget-object v2, p0, Lcom/google/android/gms/internal/zzqd$zzc;->zzbpQ:Lcom/google/android/gms/internal/zztg;

    if-eqz v2, :cond_0

    iget-object v2, p0, Lcom/google/android/gms/internal/zzqd$zzc;->zzbpQ:Lcom/google/android/gms/internal/zztg;

    invoke-virtual {v2}, Lcom/google/android/gms/internal/zztg;->isEmpty()Z

    move-result v2

    if-eqz v2, :cond_2

    :cond_0
    :goto_1
    add-int/2addr v0, v1

    return v0

    :cond_1
    iget-object v0, p0, Lcom/google/android/gms/internal/zzqd$zzc;->type:Ljava/lang/String;

    invoke-virtual {v0}, Ljava/lang/String;->hashCode()I

    move-result v0

    goto :goto_0

    :cond_2
    iget-object v1, p0, Lcom/google/android/gms/internal/zzqd$zzc;->zzbpQ:Lcom/google/android/gms/internal/zztg;

    invoke-virtual {v1}, Lcom/google/android/gms/internal/zztg;->hashCode()I

    move-result v1

    goto :goto_1
.end method

.method public synthetic mergeFrom(Lcom/google/android/gms/internal/zztc;)Lcom/google/android/gms/internal/zztk;
    .locals 1
    .param p1, "x0"    # Lcom/google/android/gms/internal/zztc;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/zzqd$zzc;->zzr(Lcom/google/android/gms/internal/zztc;)Lcom/google/android/gms/internal/zzqd$zzc;

    move-result-object v0

    return-object v0
.end method

.method public writeTo(Lcom/google/android/gms/internal/zztd;)V
    .locals 3
    .param p1, "output"    # Lcom/google/android/gms/internal/zztd;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    iget-object v0, p0, Lcom/google/android/gms/internal/zzqd$zzc;->type:Ljava/lang/String;

    const-string v1, ""

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_0

    const/4 v0, 0x1

    iget-object v1, p0, Lcom/google/android/gms/internal/zzqd$zzc;->type:Ljava/lang/String;

    invoke-virtual {p1, v0, v1}, Lcom/google/android/gms/internal/zztd;->zzb(ILjava/lang/String;)V

    :cond_0
    iget-object v0, p0, Lcom/google/android/gms/internal/zzqd$zzc;->zzaJG:[Lcom/google/android/gms/internal/zzqd$zzb;

    if-eqz v0, :cond_2

    iget-object v0, p0, Lcom/google/android/gms/internal/zzqd$zzc;->zzaJG:[Lcom/google/android/gms/internal/zzqd$zzb;

    array-length v0, v0

    if-lez v0, :cond_2

    const/4 v0, 0x0

    :goto_0
    iget-object v1, p0, Lcom/google/android/gms/internal/zzqd$zzc;->zzaJG:[Lcom/google/android/gms/internal/zzqd$zzb;

    array-length v1, v1

    if-ge v0, v1, :cond_2

    iget-object v1, p0, Lcom/google/android/gms/internal/zzqd$zzc;->zzaJG:[Lcom/google/android/gms/internal/zzqd$zzb;

    aget-object v1, v1, v0

    if-eqz v1, :cond_1

    const/4 v2, 0x2

    invoke-virtual {p1, v2, v1}, Lcom/google/android/gms/internal/zztd;->zza(ILcom/google/android/gms/internal/zztk;)V

    :cond_1
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_2
    invoke-super {p0, p1}, Lcom/google/android/gms/internal/zzte;->writeTo(Lcom/google/android/gms/internal/zztd;)V

    return-void
.end method

.method public zzr(Lcom/google/android/gms/internal/zztc;)Lcom/google/android/gms/internal/zzqd$zzc;
    .locals 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    const/4 v1, 0x0

    :cond_0
    :goto_0
    invoke-virtual {p1}, Lcom/google/android/gms/internal/zztc;->zzHi()I

    move-result v0

    sparse-switch v0, :sswitch_data_0

    invoke-virtual {p0, p1, v0}, Lcom/google/android/gms/internal/zzqd$zzc;->zza(Lcom/google/android/gms/internal/zztc;I)Z

    move-result v0

    if-nez v0, :cond_0

    :sswitch_0
    return-object p0

    :sswitch_1
    invoke-virtual {p1}, Lcom/google/android/gms/internal/zztc;->readString()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/google/android/gms/internal/zzqd$zzc;->type:Ljava/lang/String;

    goto :goto_0

    :sswitch_2
    const/16 v0, 0x12

    invoke-static {p1, v0}, Lcom/google/android/gms/internal/zztn;->zzc(Lcom/google/android/gms/internal/zztc;I)I

    move-result v2

    iget-object v0, p0, Lcom/google/android/gms/internal/zzqd$zzc;->zzaJG:[Lcom/google/android/gms/internal/zzqd$zzb;

    if-nez v0, :cond_2

    move v0, v1

    :goto_1
    add-int/2addr v2, v0

    new-array v2, v2, [Lcom/google/android/gms/internal/zzqd$zzb;

    if-eqz v0, :cond_1

    iget-object v3, p0, Lcom/google/android/gms/internal/zzqd$zzc;->zzaJG:[Lcom/google/android/gms/internal/zzqd$zzb;

    invoke-static {v3, v1, v2, v1, v0}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    :cond_1
    :goto_2
    array-length v3, v2

    add-int/lit8 v3, v3, -0x1

    if-ge v0, v3, :cond_3

    new-instance v3, Lcom/google/android/gms/internal/zzqd$zzb;

    invoke-direct {v3}, Lcom/google/android/gms/internal/zzqd$zzb;-><init>()V

    aput-object v3, v2, v0

    aget-object v3, v2, v0

    invoke-virtual {p1, v3}, Lcom/google/android/gms/internal/zztc;->zza(Lcom/google/android/gms/internal/zztk;)V

    invoke-virtual {p1}, Lcom/google/android/gms/internal/zztc;->zzHi()I

    add-int/lit8 v0, v0, 0x1

    goto :goto_2

    :cond_2
    iget-object v0, p0, Lcom/google/android/gms/internal/zzqd$zzc;->zzaJG:[Lcom/google/android/gms/internal/zzqd$zzb;

    array-length v0, v0

    goto :goto_1

    :cond_3
    new-instance v3, Lcom/google/android/gms/internal/zzqd$zzb;

    invoke-direct {v3}, Lcom/google/android/gms/internal/zzqd$zzb;-><init>()V

    aput-object v3, v2, v0

    aget-object v0, v2, v0

    invoke-virtual {p1, v0}, Lcom/google/android/gms/internal/zztc;->zza(Lcom/google/android/gms/internal/zztk;)V

    iput-object v2, p0, Lcom/google/android/gms/internal/zzqd$zzc;->zzaJG:[Lcom/google/android/gms/internal/zzqd$zzb;

    goto :goto_0

    nop

    :sswitch_data_0
    .sparse-switch
        0x0 -> :sswitch_0
        0xa -> :sswitch_1
        0x12 -> :sswitch_2
    .end sparse-switch
.end method

.method public zzxI()Lcom/google/android/gms/internal/zzqd$zzc;
    .locals 1

    const-string v0, ""

    iput-object v0, p0, Lcom/google/android/gms/internal/zzqd$zzc;->type:Ljava/lang/String;

    invoke-static {}, Lcom/google/android/gms/internal/zzqd$zzb;->zzxG()[Lcom/google/android/gms/internal/zzqd$zzb;

    move-result-object v0

    iput-object v0, p0, Lcom/google/android/gms/internal/zzqd$zzc;->zzaJG:[Lcom/google/android/gms/internal/zzqd$zzb;

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/google/android/gms/internal/zzqd$zzc;->zzbpQ:Lcom/google/android/gms/internal/zztg;

    const/4 v0, -0x1

    iput v0, p0, Lcom/google/android/gms/internal/zzqd$zzc;->zzbqb:I

    return-object p0
.end method

.method protected zzz()I
    .locals 4

    invoke-super {p0}, Lcom/google/android/gms/internal/zzte;->zzz()I

    move-result v0

    iget-object v1, p0, Lcom/google/android/gms/internal/zzqd$zzc;->type:Ljava/lang/String;

    const-string v2, ""

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_0

    const/4 v1, 0x1

    iget-object v2, p0, Lcom/google/android/gms/internal/zzqd$zzc;->type:Ljava/lang/String;

    invoke-static {v1, v2}, Lcom/google/android/gms/internal/zztd;->zzp(ILjava/lang/String;)I

    move-result v1

    add-int/2addr v0, v1

    :cond_0
    iget-object v1, p0, Lcom/google/android/gms/internal/zzqd$zzc;->zzaJG:[Lcom/google/android/gms/internal/zzqd$zzb;

    if-eqz v1, :cond_3

    iget-object v1, p0, Lcom/google/android/gms/internal/zzqd$zzc;->zzaJG:[Lcom/google/android/gms/internal/zzqd$zzb;

    array-length v1, v1

    if-lez v1, :cond_3

    const/4 v1, 0x0

    move v2, v0

    :goto_0
    iget-object v0, p0, Lcom/google/android/gms/internal/zzqd$zzc;->zzaJG:[Lcom/google/android/gms/internal/zzqd$zzb;

    array-length v0, v0

    if-ge v1, v0, :cond_2

    iget-object v0, p0, Lcom/google/android/gms/internal/zzqd$zzc;->zzaJG:[Lcom/google/android/gms/internal/zzqd$zzb;

    aget-object v0, v0, v1

    if-eqz v0, :cond_1

    const/4 v3, 0x2

    invoke-static {v3, v0}, Lcom/google/android/gms/internal/zztd;->zzc(ILcom/google/android/gms/internal/zztk;)I

    move-result v0

    add-int/2addr v2, v0

    :cond_1
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    goto :goto_0

    :cond_2
    move v0, v2

    :cond_3
    return v0
.end method