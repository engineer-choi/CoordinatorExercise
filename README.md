### CoordinatorLayout을 이용한 Material Design

**CoordinatorLayout은 화면 스크롤시 AppBar와 RecyclerView의 연동을 다루거나,  BottomSheet를 이용할 때 사용되는 레이아웃이다**

## CoordinatorLayout으로 해당 요소들을 감싸주는 것이 기본이다.


# &#128215; (1) AppBar와 RecyclerView의 연동

![appbar](https://user-images.githubusercontent.com/54485132/76851628-ee3e6c80-688c-11ea-91a9-775eb2c0663b.gif)

## 1. 스타일 적용하기
```xml
     <style name="Lab3Theme" parent="Theme.AppCompat.Light.NoActionBar">
        <item name="windowActionBar">false</item>
        <item name="android:windowNoTitle">true</item>
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
    </style>
```

## 2. Manifest에 스타일 적용하기
![manifest](https://user-images.githubusercontent.com/54485132/76851631-ef6f9980-688c-11ea-85bc-1426ba56d6a5.png)


## 3. gradle 설정하기
> 아래 코드를 추가한다

```
implementation 'com.google.android.material:material:1.1.0’
```

## 4. 레이아웃 짜기

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

## 5. 상단바 설정
Lab18_3Activity에서 
```kotlin
        val toolBar: Toolbar = findViewById(R.id.lab3_toolbar) as Toolbar
        setSupportActionBar(toolBar)
```
으로 상단바를 연동해주면 된다.



# &#128215;(2) BottomSheet 사용하기

**BottomSheet는 일반적인 것과 다이얼로그 형태(modal) 두 가지가 있다.**
<center>
    <div>
        <img src="https://user-images.githubusercontent.com/54485132/76853003-d9afa380-688f-11ea-9512-3513ba92b70f.png" width="30%">
        <img src="https://user-images.githubusercontent.com/54485132/76852995-d6b4b300-688f-11ea-981f-fa41922fb1fe.png" width="30%">
        <img src="https://user-images.githubusercontent.com/54485132/76852999-d87e7680-688f-11ea-9bdd-01f5a1648721.png" width="30%">
    </div>
</center>

![GIF](https://user-images.githubusercontent.com/54485132/76852989-d4525900-688f-11ea-9357-12fa82405f8d.gif)


**구현 방법은 다음과 같다.**


## 1. gradle 설정
```
  implementation 'com.google.android.material:material:1.1.0'
  implementation 'com.android.support:appcompat-v7:29.0.0'
  implementation 'com.android.support:design:29.0.0’
```

## 2. layout 파일
#### (1) lab4_sheet_row.xml -> 모달 시트에 들어갈 리사이클러뷰의 아이템 뷰
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="56dp"
    android:gravity="center_vertical"
    android:orientation="horizontal"
    android:padding="16dp">

    <ImageView
        android:id="@+id/lab4_sheet_row_imageView"
        android:layout_width="24dp"
        android:layout_height="24dp"/>

    <TextView
        android:id="@+id/lab4_sheet_row_textView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginLeft="32dp"/>
</LinearLayout>
```
#### (2) lab4_modal_sheet.xml -> 모달 시트에 들어갈 리사이클러뷰
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.recyclerview.widget.RecyclerView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/lab4_recyclerView"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="16dp"
    app:behavior_peekHeight="120dp" />
```

#### (3) activity_lab18_4.xml

> **Bottom Sheet로 이용할 부분을 LinearLayout에 넣어두고,
app:behavior...들로 설정함.
peekHeight는 기본 높이, hidable 속성으로 숨기거나 올릴 수 있다.**

![layout](https://user-images.githubusercontent.com/54485132/76853003-d9afa380-688f-11ea-9512-3513ba92b70f.png)

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.coordinatorlayout.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:id="@+id/lab4_coordinator"
    android:layout_height="match_parent"
    tools:context=".Lab18_4Activity">
    <Button
        android:id="@+id/lab4_button"
        android:layout_margin="32dp"
        android:text="Modal Bottom Sheet"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>

    <LinearLayout
        android:id="@+id/lab4_bottom_sheet"
        android:layout_width="match_parent"
        android:layout_height="250dp"
        android:background="#ebebeb"
        android:orientation="vertical"
        android:padding="16dp"
    app:layout_behavior="com.google.android.material.bottomsheet.BottomSheetBehavior"
        app:behavior_peekHeight = "120dp"
        app:behavior_hideable = "false">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Persistent Bottom sheets"
            android:textColor="#df000000"
            android:textSize="20dp"/>

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginBottom="16dp"
            android:layout_marginTop="16dp"
            android:text="Persistent bottom sheets"
            android:textColor="#000000"
            android:textSize="16dp"/>

    </LinearLayout>

</androidx.coordinatorlayout.widget.CoordinatorLayout>
```

## 3. Lab4RecyclerViewAdapter.kt

> ViewHolder와 Data Class를 갖고 있는 Adapter

```kotlin
class Lab4RecyclerViewAdapter(private val list: List<DataVO>) :
    RecyclerView.Adapter<ItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val root =
            LayoutInflater.from(parent.context).inflate(R.layout.lab4_sheet_row, parent, false)
        return ItemHolder(root)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val vo = list[position]
        holder.textView.text = vo.title
        holder.imageView.setImageDrawable(vo.image)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class ItemHolder(root: View?) :
    RecyclerView.ViewHolder(root!!) {
    var textView: TextView
    var imageView: ImageView

    init {
        imageView =
            itemView.findViewById<View>(R.id.lab4_sheet_row_imageView) as ImageView
        textView =
            itemView.findViewById<View>(R.id.lab4_sheet_row_textView) as TextView
    }
}

class DataVO {
    var title: String? = null
    var image: Drawable? = null
}
```

## 4. Lab18_4Activity.kt
```kotlin
class Lab18_4Activity : AppCompatActivity(), View.OnClickListener {

    lateinit var persistentBottomSeet : BottomSheetBehavior<View>
    lateinit var modalBottomSeet : BottomSheetDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab18_4)
        init()
    }

    private fun init(){
        lab4_button.setOnClickListener(this)
        initPersistentBottomSheet()
    }
    override fun onClick(v: View?) {
        createDialog()
    }
    private fun createDialog(){
        val list = mutableListOf<DataVO>()

        val vo = DataVO()
        vo.title = "Keep"
        vo.image = ResourcesCompat.getDrawable(resources,R.drawable.appbar_image,null)
        list.add(vo)
        val vo1 = DataVO()
        val vo2 = DataVO()
        val vo3 = DataVO()
        vo1.title = "Inbox"
        vo1.image = ResourcesCompat.getDrawable(resources,R.drawable.appbar_image,null)
        list.add(vo1)
        vo2.title = "asfd"
        vo2.image = ResourcesCompat.getDrawable(resources,R.drawable.appbar_image,null)
        list.add(vo2)
        vo3.title = "sfasdx"
        vo3.image = ResourcesCompat.getDrawable(resources,R.drawable.appbar_image,null)
        list.add(vo3)

        val adapter = Lab4RecyclerViewAdapter(list)
        val view = layoutInflater.inflate(R.layout.lab4_modal_sheet,null)
        val rv = view.findViewById<RecyclerView>(R.id.lab4_recyclerView)
        rv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rv.adapter = adapter

        modalBottomSeet = BottomSheetDialog(this)
        modalBottomSeet.setContentView(view)
        modalBottomSeet.show()
    }
    private fun initPersistentBottomSheet(){
        val bottomSheet = lab4_bottom_sheet
        persistentBottomSeet = BottomSheetBehavior.from(bottomSheet)
    }
}
```
