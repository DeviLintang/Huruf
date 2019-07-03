.class public Lcom/google/unity/ads/Banner;
.super Ljava/lang/Object;
.source "Banner.java"


# static fields
.field private static final POSITION_BOTTOM:I = 0x1

.field private static final POSITION_BOTTOM_LEFT:I = 0x4

.field private static final POSITION_BOTTOM_RIGHT:I = 0x5

.field private static final POSITION_TOP:I = 0x0

.field private static final POSITION_TOP_LEFT:I = 0x2

.field private static final POSITION_TOP_RIGHT:I = 0x3


# instance fields
.field private activity:Landroid/app/Activity;

.field private adView:Lcom/google/android/gms/ads/AdView;

.field private listener:Lcom/google/unity/ads/UnityAdListener;


# direct methods
.method public constructor <init>(Landroid/app/Activity;Lcom/google/unity/ads/UnityAdListener;)V
    .locals 0
    .param p1, "activity"    # Landroid/app/Activity;
    .param p2, "listener"    # Lcom/google/unity/ads/UnityAdListener;

    .prologue
    .line 58
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 59
    iput-object p1, p0, Lcom/google/unity/ads/Banner;->activity:Landroid/app/Activity;

    .line 60
    iput-object p2, p0, Lcom/google/unity/ads/Banner;->listener:Lcom/google/unity/ads/UnityAdListener;

    .line 61
    return-void
.end method

.method static synthetic access$000(Lcom/google/unity/ads/Banner;)Lcom/google/android/gms/ads/AdView;
    .locals 1
    .param p0, "x0"    # Lcom/google/unity/ads/Banner;

    .prologue
    .line 24
    iget-object v0, p0, Lcom/google/unity/ads/Banner;->adView:Lcom/google/android/gms/ads/AdView;

    return-object v0
.end method

.method static synthetic access$002(Lcom/google/unity/ads/Banner;Lcom/google/android/gms/ads/AdView;)Lcom/google/android/gms/ads/AdView;
    .locals 0
    .param p0, "x0"    # Lcom/google/unity/ads/Banner;
    .param p1, "x1"    # Lcom/google/android/gms/ads/AdView;

    .prologue
    .line 24
    iput-object p1, p0, Lcom/google/unity/ads/Banner;->adView:Lcom/google/android/gms/ads/AdView;

    return-object p1
.end method

.method static synthetic access$100(Lcom/google/unity/ads/Banner;)Landroid/app/Activity;
    .locals 1
    .param p0, "x0"    # Lcom/google/unity/ads/Banner;

    .prologue
    .line 24
    iget-object v0, p0, Lcom/google/unity/ads/Banner;->activity:Landroid/app/Activity;

    return-object v0
.end method

.method static synthetic access$200(Lcom/google/unity/ads/Banner;)Lcom/google/unity/ads/UnityAdListener;
    .locals 1
    .param p0, "x0"    # Lcom/google/unity/ads/Banner;

    .prologue
    .line 24
    iget-object v0, p0, Lcom/google/unity/ads/Banner;->listener:Lcom/google/unity/ads/UnityAdListener;

    return-object v0
.end method


# virtual methods
.method public create(Ljava/lang/String;Lcom/google/android/gms/ads/AdSize;I)V
    .locals 2
    .param p1, "publisherId"    # Ljava/lang/String;
    .param p2, "adSize"    # Lcom/google/android/gms/ads/AdSize;
    .param p3, "positionCode"    # I

    .prologue
    .line 71
    iget-object v0, p0, Lcom/google/unity/ads/Banner;->activity:Landroid/app/Activity;

    new-instance v1, Lcom/google/unity/ads/Banner$1;

    invoke-direct {v1, p0, p1, p2, p3}, Lcom/google/unity/ads/Banner$1;-><init>(Lcom/google/unity/ads/Banner;Ljava/lang/String;Lcom/google/android/gms/ads/AdSize;I)V

    invoke-virtual {v0, v1}, Landroid/app/Activity;->runOnUiThread(Ljava/lang/Runnable;)V

    .line 141
    return-void
.end method

.method public destroy()V
    .locals 2

    .prologue
    .line 194
    iget-object v0, p0, Lcom/google/unity/ads/Banner;->activity:Landroid/app/Activity;

    new-instance v1, Lcom/google/unity/ads/Banner$5;

    invoke-direct {v1, p0}, Lcom/google/unity/ads/Banner$5;-><init>(Lcom/google/unity/ads/Banner;)V

    invoke-virtual {v0, v1}, Landroid/app/Activity;->runOnUiThread(Ljava/lang/Runnable;)V

    .line 205
    return-void
.end method

.method public hide()V
    .locals 2

    .prologue
    .line 180
    iget-object v0, p0, Lcom/google/unity/ads/Banner;->activity:Landroid/app/Activity;

    new-instance v1, Lcom/google/unity/ads/Banner$4;

    invoke-direct {v1, p0}, Lcom/google/unity/ads/Banner$4;-><init>(Lcom/google/unity/ads/Banner;)V

    invoke-virtual {v0, v1}, Landroid/app/Activity;->runOnUiThread(Ljava/lang/Runnable;)V

    .line 188
    return-void
.end method

.method public loadAd(Lcom/google/android/gms/ads/AdRequest;)V
    .locals 2
    .param p1, "request"    # Lcom/google/android/gms/ads/AdRequest;

    .prologue
    .line 153
    iget-object v0, p0, Lcom/google/unity/ads/Banner;->activity:Landroid/app/Activity;

    new-instance v1, Lcom/google/unity/ads/Banner$2;

    invoke-direct {v1, p0, p1}, Lcom/google/unity/ads/Banner$2;-><init>(Lcom/google/unity/ads/Banner;Lcom/google/android/gms/ads/AdRequest;)V

    invoke-virtual {v0, v1}, Landroid/app/Activity;->runOnUiThread(Ljava/lang/Runnable;)V

    .line 160
    return-void
.end method

.method public setAdListener(Lcom/google/unity/ads/UnityAdListener;)V
    .locals 0
    .param p1, "listener"    # Lcom/google/unity/ads/UnityAdListener;

    .prologue
    .line 144
    iput-object p1, p0, Lcom/google/unity/ads/Banner;->listener:Lcom/google/unity/ads/UnityAdListener;

    .line 145
    return-void
.end method

.method public show()V
    .locals 2

    .prologue
    .line 166
    iget-object v0, p0, Lcom/google/unity/ads/Banner;->activity:Landroid/app/Activity;

    new-instance v1, Lcom/google/unity/ads/Banner$3;

    invoke-direct {v1, p0}, Lcom/google/unity/ads/Banner$3;-><init>(Lcom/google/unity/ads/Banner;)V

    invoke-virtual {v0, v1}, Landroid/app/Activity;->runOnUiThread(Ljava/lang/Runnable;)V

    .line 174
    return-void
.end method
