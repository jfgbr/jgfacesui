package com.jgalante.jgfaces.component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.primefaces.component.api.UIColumn;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.datatable.DataTableRenderer;
import org.primefaces.component.datatable.feature.DataTableFeature;
import org.primefaces.component.datatable.feature.DataTableFeatureKey;
import org.primefaces.component.datatable.feature.DraggableColumnsFeature;
import org.primefaces.component.datatable.feature.FilterFeature;
import org.primefaces.component.datatable.feature.PageFeature;
import org.primefaces.component.datatable.feature.ResizableColumnsFeature;
import org.primefaces.component.datatable.feature.RowEditFeature;
import org.primefaces.component.datatable.feature.RowExpandFeature;
import org.primefaces.component.datatable.feature.ScrollFeature;
import org.primefaces.component.datatable.feature.SelectionFeature;
import org.primefaces.component.datatable.feature.SortFeature;
import org.primefaces.model.SortMeta;
import org.primefaces.util.HTML;

public class DataTableRendererExt extends DataTableRenderer {
	
	private final static Logger logger = Logger.getLogger(DataTableRendererExt.class.getName());
	
	private final String ATTR_FILTER_ENABLED = "filterEnabled";
	private boolean filterEnabled = false;
	
	static Map<DataTableFeatureKey,DataTableFeature> FEATURES;
    
    static {
        FEATURES = new HashMap<DataTableFeatureKey,DataTableFeature>();
        FEATURES.put(DataTableFeatureKey.DRAGGABLE_COLUMNS, new DraggableColumnsFeature());
        FEATURES.put(DataTableFeatureKey.FILTER, new FilterFeature());
        FEATURES.put(DataTableFeatureKey.PAGE, new PageFeature());
        FEATURES.put(DataTableFeatureKey.SORT, new SortFeature());
        FEATURES.put(DataTableFeatureKey.RESIZABLE_COLUMNS, new ResizableColumnsFeature());
        FEATURES.put(DataTableFeatureKey.SELECT, new SelectionFeature());
        FEATURES.put(DataTableFeatureKey.ROW_EDIT, new RowEditFeature());
        FEATURES.put(DataTableFeatureKey.ROW_EXPAND, new RowExpandFeature());
        FEATURES.put(DataTableFeatureKey.SCROLL, new ScrollFeature());
    }
	
	@Override
	public void encodeEnd(FacesContext context, UIComponent component)
			throws IOException {

		DataTable table = (DataTable) component;
		ValueExpression tableLoadEnabledVe = table.getValueExpression("loadEnabled");
		boolean loadEnabled = true;
		
		if (tableLoadEnabledVe != null) {
			loadEnabled = (Boolean)tableLoadEnabledVe.getValue(context.getELContext());
		}
		

        if(table.shouldEncodeFeature(context)) {
            for(Iterator<DataTableFeature> it = FEATURES.values().iterator(); it.hasNext();) {
                DataTableFeature feature = it.next();

                if(feature.shouldEncode(context, table)) {
                    feature.encode(context, this, table);
                }
            }
        }
        else {
            if(table.isLazy() && loadEnabled) {
                table.loadLazyData();
            }
                    
            encodeMarkup(context, table);
            encodeScript(context, table);
        }
	}
	
	@Override
	protected void encodeMarkup(FacesContext context, DataTable table)
			throws IOException {
		table.reset();
		super.encodeMarkup(context, table);
	}
	
	@Override
	protected void encodeThead(FacesContext context, DataTable table)
			throws IOException {
		ValueExpression tableFilterEnabledVe = table.getValueExpression(ATTR_FILTER_ENABLED);
		if (tableFilterEnabledVe == null) {
			filterEnabled = true;
		} else {
			filterEnabled = (Boolean)tableFilterEnabledVe.getValue(context.getELContext());
		}
		super.encodeThead(context, table);
	}
	
	@Override
	protected void encodeColumnHeader(FacesContext context, DataTable table,
			UIColumn column) throws IOException {
		if(!column.isRendered()) {
            return;
        }
        
        ResponseWriter writer = context.getResponseWriter();
        String clientId = column.getContainerClientId(context);

        ValueExpression columnSortByVE = column.getValueExpression("sortBy");
        Object columnSortByProperty = column.getSortBy();
        boolean sortable = (columnSortByVE != null || columnSortByProperty != null);
        Object filterByProperty = column.getFilterBy();
        boolean hasFilter = (column.getValueExpression("filterBy") != null || filterByProperty != null) && filterEnabled;
        String selectionMode = column.getSelectionMode();
        String sortIcon = null;
        boolean resizable = table.isResizableColumns() && column.isResizable();
        
        if(columnSortByProperty != null || filterByProperty != null) {
            logger.warning("Defining fields in sortBy-filterBy attributes is deprecated use a value expression instead. e.g. sortBy=\"#{user.name}\" instead of sortBy=\"name\"");
        }
        
        String columnClass = sortable ? DataTable.COLUMN_HEADER_CLASS + " " + DataTable.SORTABLE_COLUMN_CLASS : DataTable.COLUMN_HEADER_CLASS;
        columnClass = hasFilter ? columnClass + " " + DataTable.FILTER_COLUMN_CLASS : columnClass;
        columnClass = selectionMode != null ? columnClass + " " + DataTable.SELECTION_COLUMN_CLASS : columnClass;
        columnClass = resizable ? columnClass + " " + DataTable.RESIZABLE_COLUMN_CLASS : columnClass;
        columnClass = !column.isToggleable() ? columnClass + " " + DataTable.STATIC_COLUMN_CLASS : columnClass;
        columnClass = column.getStyleClass() != null ? columnClass + " " + column.getStyleClass() : columnClass;
        
        if(sortable) {
            ValueExpression tableSortByVE = table.getValueExpression("sortBy");
            Object tableSortBy = table.getSortBy();
            boolean defaultSorted = (tableSortByVE != null || tableSortBy != null);
                    
            if(defaultSorted) {
                if(table.isMultiSort()) {
                    List<SortMeta> sortMeta = table.getMultiSortMeta();
                    
                    if(sortMeta != null) {
                        for(SortMeta meta : sortMeta) {
                            UIColumn sortColumn = meta.getColumn();
                            sortIcon = resolveDefaultSortIcon(columnSortByVE, columnSortByProperty, 
                                    sortColumn.getValueExpression("sortBy"), sortColumn.getSortBy(), meta.getSortOrder().name());

                            if(sortIcon != null) {
                                break;
                            }
                        }
                    }
                }
                else {
                    sortIcon = resolveDefaultSortIcon(columnSortByVE, columnSortByProperty, tableSortByVE, tableSortBy, table.getSortOrder());
                }
            }
            
            if(sortIcon == null)
                sortIcon = DataTable.SORTABLE_COLUMN_ICON_CLASS;
            else
                columnClass += " ui-state-active";
        }
        
        String style = column.getStyle();
        String width = column.getWidth();
        if(width != null) {
            String unit = width.endsWith("%") ? "" : "px";
            if(style != null)
                style = style + ";width:" + width + unit;
            else
                style = "width:" + width + unit;
        }
        
        writer.startElement("th", null);
        writer.writeAttribute("id", clientId, null);
        writer.writeAttribute("class", columnClass, null);
        writer.writeAttribute("role", "columnheader", null);
        
        if(style != null) writer.writeAttribute("style", style, null);
        if(column.getRowspan() != 1) writer.writeAttribute("rowspan", column.getRowspan(), null);
        if(column.getColspan() != 1) writer.writeAttribute("colspan", column.getColspan(), null);
        
        if(hasFilter) {
            table.enableFiltering();

            String filterPosition = column.getFilterPosition();

            if(filterPosition.equals("bottom")) {
                encodeColumnHeaderContent(context, column, sortIcon);
                encodeFilter(context, table, column);
            }
            else if(filterPosition.equals("top")) {
                encodeFilter(context, table, column);
                encodeColumnHeaderContent(context, column, sortIcon);
            } 
            else {
                throw new FacesException(filterPosition + " is an invalid option for filterPosition, valid values are 'bottom' or 'top'.");
            }
        }
        else {
            encodeColumnHeaderContent(context, column, sortIcon);
        }
        
        if(selectionMode != null && selectionMode.equalsIgnoreCase("multiple")) {
            encodeCheckbox(context, table, false, false, HTML.CHECKBOX_ALL_CLASS);
        }
        
        writer.endElement("th");
	}
	
}
