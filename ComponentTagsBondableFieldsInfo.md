# Introduction #

This page will contain brief overview regarding bondable fields of JSF Flex components Tags which have reference in **preserving the value/state during `postBack`**. Please check out mxmlOverallExample.xhtml and mxmlOverallExample.jsp pages for comprehensive viewing of these and additional data bounded components.

# Details #

  * `Accordion`
    1. selectedIndex _Integer_ : contains info regarding which container has been selected.

  * `CheckBox`
    1. selected _Boolean_ : contains the boolean of whether user has selected the checkbox or not.

  * `ColorPicker`
    1. selectedColor _String_ : contains the color chosen for the colorPicker

  * `ComboBox`
    1. text _String_ : contains the comboxBox's text that the user has selected
    1. selectedIndex _Integer_ : contains the comboxBox that the user has selected {in order to preserve the correct selection, this field must be mapped within the managed beans}.

  * `DataGrid`
    1. bindingBeanList _List_ : List of beans for asynchronous retrieval and update of data. The bean should have corresponding get + set methods for each `DataGridColumn's` dataField attributes and the data should implement the _Comparable_ interface for sorting.

  * `DateChooser`
    1. selectedDate _Calendar_ : the selected date of the component.

  * `DateField`
    1. text _String_ : contains the date selected by the user.

  * `HorizontalList`
    1. selectedIndex _Integer_ : contains the list element that the user has selected.

  * `HSlider`
    1. value _String_ : contains the slider's value.

  * `List`
    1. selectedIndex _Integer_ : contains the list element that the user has selected.

  * `NumericStepper`
    1. value _String_ : contains the value of the numericStepper

  * `ObjectListEntries `
    1. bindingBeanList _List_ : List of beans to be used for `ObjectElement + ObjectProperty` childrens, with the field of `ObjectProperty's` property being fetched via reflection.

  * `ProgressBar`
    1. value _String_ : the progressBar value. **Note that this field will not be set upon loading of the app, since it's read-only.**

  * `RadioButton`
    1. selectedValue _String_ : contains the value selected within the same groupName radioButtons.
    1. selected _Boolean_ : contains the boolean flag of whether the current radioButton has been selected or not {in order to preserve the correct selection, this field must be mapped within the managed beans}.

  * `RichTextEditor`
    1. text _String_ : contains the text inputed by the user.
    1. htmlText _String_ : contains the htmlText {in rich text format specified through xml tags} inputed by the user. Have to set `textBinding` field to `htmlText`.

  * `TabNavigator`
    1. selectedIndex _Integer_ : contains info regarding which container has been selected.

  * `TextArea`
    1. text _String_ : contains the text inputed by the user.

  * `TextInput`
    1. text _String_ : contains the text inputed by the user.

  * `TileList`
    1. selectedIndex _Integer_ : contains the list element that the user has selected.

  * `Tree`
    1. selectedIndex _Integer_ : contains the top Tree element that the user has selected.

  * `ViewStack`
    1. selectedIndex _Integer_ : contains the top Tree element that the user has selected.

  * `VSlider`
    1. value _String_ : contains the slider's value.

  * `XMLListEntries`
    1. bindingBeanList _List_ : List of beans to be used for `XMLElement + XMLAttribute` childrens, which will fetch the fields via reflection. {note that these values will be static meaning the values during debugMode will be the values during productionMode even if the beans change}.