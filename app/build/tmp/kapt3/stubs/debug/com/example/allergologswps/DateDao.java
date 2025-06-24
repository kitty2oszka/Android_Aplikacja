package com.example.allergologswps;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0005J\u0014\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00030\u0007H\'J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u00a7@\u00a2\u0006\u0002\u0010\f\u00a8\u0006\r"}, d2 = {"Lcom/example/allergologswps/DateDao;", "", "getAllDates", "", "Lcom/example/allergologswps/DateEntity;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMostFrequentProducts", "Landroidx/lifecycle/LiveData;", "Lcom/example/allergologswps/models/FrequentProduct;", "insertDate", "", "dateEntity", "(Lcom/example/allergologswps/DateEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface DateDao {
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertDate(@org.jetbrains.annotations.NotNull()
    com.example.allergologswps.DateEntity dateEntity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM DateEntity")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllDates(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.allergologswps.DateEntity>> $completion);
    
    @androidx.room.Query(value = "\n        SELECT imageUrl, COUNT(imageUrl) as count\n        FROM DateEntity\n        GROUP BY imageUrl\n        ORDER BY count DESC\n    ")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.example.allergologswps.models.FrequentProduct>> getMostFrequentProducts();
}