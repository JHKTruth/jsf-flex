# Introduction #

This page provides a quick overview of the Flex's `DataGrid` component which has been augmented to support server side binding for large data size.

# Details #

## Remote Grid Filtering ##
  * Initial view of the Remote `DataGrid` with the usage of `TextInput` component for the filtering.
> > ![http://jsf-flex.googlecode.com/svn/wiki/initialRemoteGridFilterView.jpg](http://jsf-flex.googlecode.com/svn/wiki/initialRemoteGridFilterView.jpg)
  * Filtering of the `DataGrid` with the value of 1 `[filtering currently is done by usage of listener, but in a similar manner to updates, request for filtering will be done by setInterval for better performance]`.
> > ![http://jsf-flex.googlecode.com/svn/wiki/firstRemoteGridFilterView.jpg](http://jsf-flex.googlecode.com/svn/wiki/firstRemoteGridFilterView.jpg)
  * After several number of filtering for the `DataGrid`.
> > ![http://jsf-flex.googlecode.com/svn/wiki/severalRemoteGridFilterView.jpg](http://jsf-flex.googlecode.com/svn/wiki/severalRemoteGridFilterView.jpg)
  * Update to a row within the `DataGrid` to show synchronization on the server side.
> > ![http://jsf-flex.googlecode.com/svn/wiki/fieldUpdateToRemoteGridFilterView.jpg](http://jsf-flex.googlecode.com/svn/wiki/fieldUpdateToRemoteGridFilterView.jpg)
  * Clearing of the filtering to its original state, so to move the cache out and to get the new data from the server side.
> > ![http://jsf-flex.googlecode.com/svn/wiki/clearingOfRemoteGridFilterView.jpg](http://jsf-flex.googlecode.com/svn/wiki/clearingOfRemoteGridFilterView.jpg)
  * Check to see that the update has been requested to the server side prior to clearing of the cache `[update requests will be made as batch at a time interval with a check or if a trigger for data or other action is requested prior to the interval check, the action will wait until the update transaction has been completed]`.
> > ![http://jsf-flex.googlecode.com/svn/wiki/checkToSeeModificationPreservedOfRemoteGridView.jpg](http://jsf-flex.googlecode.com/svn/wiki/checkToSeeModificationPreservedOfRemoteGridView.jpg)
  * XHTML content for the filtering Remote Grid code. Note that `TextInput` component is not the only component that the `DataGrid` can be filtered by, technically any component which can have the values bound to the server side component should be able to be used.
```
<jf:flexTextInput id="filteringComponent" text="#{flexFaceletOverallBean.textInputText}" />

<jf:flexDataGrid bindingBeanList="#{multipleRequestDataGridExample.largeDataEntries}" rowCount="10" editable="true" filterComponentId="filteringComponent" filterEventListener="keyUp" queueFilterThreshold="1" queuedFilterListBreakUpSize="500">
    <jf:flexAttributeNode name="resizableColumns" value="true"/>
    <jf:flexAttributeNode name="width" value="100%"/>
    				
    <jf:flexColumns>
        <jf:flexDataGridColumn dataField="firstColumnEntry">
            <jf:flexAttributeNode name="headerText" value="First Column Entry"/>
        </jf:flexDataGridColumn>
        <jf:flexDataGridColumn dataField="secondColumnEntry">
	    <jf:flexAttributeNode name="headerText" value="Second Column Entry"/>
	    <f:convertNumber />
	</jf:flexDataGridColumn>
    </jf:flexColumns>
</jf:flexDataGrid>
```


## Remote Grid Drag Drop ##
  * Initial view of the two Remote `DataGrid` components.
> > ![http://jsf-flex.googlecode.com/svn/wiki/initialRemoteGridDragDropView.jpg](http://jsf-flex.googlecode.com/svn/wiki/initialRemoteGridDragDropView.jpg)
  * Initiation of the drag + drop of rows from one Remote `DataGrid` component to an another Remote `DataGrid` component.
> > ![http://jsf-flex.googlecode.com/svn/wiki/dragDropInitRemoteGridDragDropView.jpg](http://jsf-flex.googlecode.com/svn/wiki/dragDropInitRemoteGridDragDropView.jpg)
  * After the drag + drop of rows has been completed from one Remote `DataGrid` component to an another Remote `DataGrid` component `[note that the rows were removed from the source component due to dragMoveEnabled attribute being set to true]`.
> > ![http://jsf-flex.googlecode.com/svn/wiki/dragDropEndRemoteGridDragDropView.jpg](http://jsf-flex.googlecode.com/svn/wiki/dragDropEndRemoteGridDragDropView.jpg)
  * Reverse sorting the `DataGrid` component to clear the cache.
> > ![http://jsf-flex.googlecode.com/svn/wiki/reverseSortRemoteGridDragDropView.jpg](http://jsf-flex.googlecode.com/svn/wiki/reverseSortRemoteGridDragDropView.jpg)
  * Original sorting to show the synchronization with the server side.
> > ![http://jsf-flex.googlecode.com/svn/wiki/originalSortRemoteGridDragDropView.jpg](http://jsf-flex.googlecode.com/svn/wiki/originalSortRemoteGridDragDropView.jpg)
  * XHTML content for the drag drop Remote Grid code. One interesting attribute `[others are mostly standard Flex attributes]` is `bindingBeanClassName` which must be defined if the target `DataGrid` Remote component can start with empty content for the component will utilize the first entry within `bindingBeanList` if it is not empty or use the `bindingBeanClassName` attribute if it is empty to instantiate the class.
```
<jf:flexDataGrid bindingBeanList="#{multipleRequestDataGridExample.largeDataEntries}" rowCount="10">
    <jf:flexAttributeNode name="width" value="100%"/>
    <jf:flexAttributeNode name="resizableColumns" value="true"/>
    <jf:flexAttributeNode name="dragEnabled" value="true"/>
    <jf:flexAttributeNode name="dragMoveEnabled" value="true"/>
    <jf:flexAttributeNode name="allowMultipleSelection" value="true"/>
    
    <jf:flexColumns>
        <jf:flexDataGridColumn dataField="firstColumnEntry">
            <jf:flexAttributeNode name="headerText" value="First Column Entry"/>
	</jf:flexDataGridColumn>
	<jf:flexDataGridColumn dataField="secondColumnEntry">
            <jf:flexAttributeNode name="headerText" value="Second Column Entry"/>
	    <f:convertNumber />
        </jf:flexDataGridColumn>
    </jf:flexColumns>
</jf:flexDataGrid>

<jf:flexDataGrid bindingBeanList="#{multipleRequestDataGridExample.largeSecondDataEntries}" rowCount="10" editable="true"		
bindingBeanClassName="com.googlecode.jsfFlex.examples.flex.multipleRequestDataGridExample.MultipleRequestDataGridExample$LargeDataEntry">
    <jf:flexAttributeNode name="resizableColumns" value="true"/>
    <jf:flexAttributeNode name="width" value="100%"/>
    <jf:flexAttributeNode name="dropEnabled" value="true"/>
    <jf:flexAttributeNode name="allowMultipleSelection" value="true"/>

    <jf:flexColumns>
        <jf:flexDataGridColumn dataField="firstColumnEntry">
	    <jf:flexAttributeNode name="headerText" value="First Column Entry"/>
        </jf:flexDataGridColumn>
	<jf:flexDataGridColumn dataField="secondColumnEntry">
	    <jf:flexAttributeNode name="headerText" value="Second Column Entry"/>
	    <f:convertNumber />
	</jf:flexDataGridColumn>
    </jf:flexColumns>
</jf:flexDataGrid>
```