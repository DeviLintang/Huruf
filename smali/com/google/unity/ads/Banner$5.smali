.class Lcom/google/unity/ads/Banner$5;
.super Ljava/lang/Object;
.source "Banner.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/google/unity/ads/Banner;->destroy()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/google/unity/ads/Banner;


# direct methods
.method constructor <init>(Lcom/google/unity/ads/Banner;)V
    .locals 0

    .prologue
    .line 194
    iput-object p1, p0, Lcom/google/unity/ads/Banner$5;->this$0:Lcom/google/unity/ads/Banner;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 3

    .prologue
    .line 197
    const-string v1, "AdsUnity"

    const-string v2, "Calling destroy() on Android"

    invoke-static {v1, v2}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 198
    iget-object v1, p0, Lcom/google/unity/ads/Banner$5;->this$0:Lcom/google/unity/ads/Banner;

    invoke-static {v1}, Lcom/google/unity/ads/Banner;->access$000(Lcom/google/unity/ads/Banner;)Lcom/google/android/gms/ads/AdView;

    move-result-object v1

    invoke-virtual {v1}, Lcom/google/android/gms/ads/AdView;->destroy()V

    .line 199
    iget-object v1, p0, Lcom/google/unity/ads/Banner$5;->this$0:Lcom/google/unity/ads/Banner;

    invoke-static {v1}, Lcom/google/unity/ads/Banner;->access$000(Lcom/google/unity/ads/Banner;)Lcom/google/android/gms/ads/AdView;

    move-result-object v1

    invoke-virtual {v1}, Lcom/google/android/gms/ads/AdView;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    .line 200
    .local v0, "parentView":Landroid/view/ViewParent;
    if-eqz v0, :cond_0

    instance-of v1, v0, Landroid/view/ViewGroup;

    if-eqz v1, :cond_0

    .line 201
    check-cast v0, Landroid/view/ViewGroup;

    .end local v0    # "parentView":Landroid/view/ViewParent;
    iget-object v1, p0, Lcom/google/unity/ads/Banner$5;->this$0:Lcom/google/unity/ads/Banner;

    invoke-static {v1}, Lcom/google/unity/ads/Banner;->access$000(Lcom/google/unity/ads/Banner;)Lcom/google/android/gms/ads/AdView;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    .line 203
    :cond_0
    return-void
.end method