### CoordinatorLayout을 이용한 Material Design

**CoordinatorLayout은 화면 스크롤시 AppBar와 RecyclerView의 연동을 다루거나,**
**BottomSheet를 이용할 때 사용되는 레이아웃이다**

![appbar](https://user-images.githubusercontent.com/54485132/76851628-ee3e6c80-688c-11ea-91a9-775eb2c0663b.gif)

# 1. 스타일 적용하기
```xml
     <style name="Lab3Theme" parent="Theme.AppCompat.Light.NoActionBar">
        <item name="windowActionBar">false</item>
        <item name="android:windowNoTitle">true</item>
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
    </style>
```

# 2. Manifest에 스타일 적용하기
![manifest](https://user-images.githubusercontent.com/54485132/76851631-ef6f9980-688c-11ea-85bc-1426ba56d6a5.png)


# 3. gradle 설정하기
> 아래 코드를 추가한다

```
implementation 'com.google.android.material:material:1.1.0’
```

# 4. 레이아웃 짜기

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.coordinatorlayout.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/lab3_coordinator"
    android:fitsSystemWindows="true">
    <com.google.android.material.appbar.AppBarLayout
        android:id="@+id/lab3_appbar"
        android:layout_width="match_parent"
        android:layout_height="242dp"
        android:fitsSystemWindows="true"
        android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar">
        <com.google.android.material.appbar.CollapsingToolbarLayout
            android:id="@+id/lab3_collapsing"
            android:layout_width="match_parent"
            android:layout_height="242dp"
            app:contentScrim="@color/colorAccent"
            app:expandedTitleMarginBottom="50dp"
            app:expandedTitleMarginStart="32dp"
            app:layout_scrollFlags="scroll|exitUntilCollapsed"
            app:title="Hello HoJune!!">
            <ImageView
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:id="@+id/lab3_appbar_image"
                android:scaleType="centerCrop"
                android:src="@drawable/appbar_image"
                app:layout_collapseMode="parallax"/> 
<!--parallax는 스크롤시 이동, pin은 이동하지 않음.-->
            <androidx.appcompat.widget.Toolbar
                android:id="@+id/lab3_toolbar"
                android:layout_width="match_parent"
                android:layout_height="?attr/actionBarSize"
                android:layout_margin="8dp"
                app:layout_collapseMode="pin"/>
<!--parallax는 스크롤시 이동, pin은 이동하지 않음 툴바는 고정시킴-->
        </com.google.android.material.appbar.CollapsingToolbarLayout>
    </com.google.android.material.appbar.AppBarLayout>
    <androidx.recyclerview.widget.RecyclerView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/lab3_recycler"
        app:layout_behavior="@string/appbar_scrolling_view_behavior"/>

</androidx.coordinatorlayout.widget.CoordinatorLayout>
```

![layout_xml](https://user-images.githubusercontent.com/54485132/76851637-f1395d00-688c-11ea-848d-42c22e9fec9e.png)

# 5. 상단바 설정
Lab18_3Activity에서 
```kotlin
        val toolBar: Toolbar = findViewById(R.id.lab3_toolbar) as Toolbar
        setSupportActionBar(toolBar)
```
으로 상단바를 연동해주면 된다.
