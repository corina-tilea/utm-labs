
**Implemented Tasks Description:**

1. An Android Application has been created. It displays a dialog box on button click, that was added to the main activity. In order to handle the button click, was set OnClickListener, whithin which is created an AlertDialog.

2. A system menu has been added(Overflow Menu) with 3 items. On list items (group of radio buttons) has been added onClickListener, that changes the background color.
The menu was created within a .xml file: 'menu_main.xml'. In this file had been declared 3 items, included in a group, with single choice.

3. Hook keyboard input. 2 custom events had been added: one for back button click and other for volume down button.

4.  A scroll bar has been added to the view. The scroll listener is implemented to change the color of the text.
In order to handle scroll event, the activity has implemented GestureDetector.OnGestureListener interface. Implimentation of this interface implies overriding certain methods (that we want to handle), for example: 
```java
@Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        textView.setTextColor(Color.RED);
        return true;
    }
```

5. The main activity has been customized by adding an icon on a Button.
Icons can be added as drawable resources. Firs icons are declared as VectorAsset in specific .xml file
[Example]:
```xml
<vector xmlns:android="http://schemas.android.com/apk/res/android"
        android:width="24dp"
        android:height="24dp"
        android:viewportWidth="24.0"
        android:viewportHeight="24.0">
    <path
        android:fillColor="#FF000000"
        android:pathData="M12,2C6.48,2 2,6.48 2,12s4.48,10 10,10 10,-4.48 10,-10S17.52,2 12,2zM13,17h-2v-6h2v6zM13,9h-2L11,7h2v2z"/>
</vector>
```
Then we can use this icon in our views.

7. In order to work with ListView StableArrayAdapter class was created, which extends ArrayAdapter<String>. Using this class certain elements had been added to the ListView.
In order to attach some events when any element is accessed setOnItemClickListener is added to the ListView.
