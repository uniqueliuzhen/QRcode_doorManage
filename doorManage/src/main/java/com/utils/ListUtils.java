package com.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p>List工具类</p> 
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class ListUtils {

	protected static final Logger log = LoggerFactory.getLogger(ListUtils.class);

	protected static final List<String> DATE_TYPE = Arrays
			.asList(new String[] { "oracle.sql.DATE" });

	protected static final List<String> TIME_TYPE = Arrays
			.asList(new String[] { "oracle.sql.TIMESTAMP" });

	protected ListUtils() {
	}

	public static final <T> List<T> convert(T[] arr) {
		if (null == arr) {
			return new ArrayList<T>();
		}
		return Arrays.asList(arr);
	}

	public static final boolean isEmpty(Collection collection) {
		if (collection == null || collection.isEmpty())
			return true;
		else
			return false;
	}

	public static final boolean isEmpty(Map map) {
		if (map == null || map.isEmpty())
			return true;
		else
			return false;
	}

	/**
	 * 複製list
	 * 
	 * @param src
	 *          List
	 * @param dest
	 *          List
	 */
	public static void arraycopy(List src, List dest) {
		for (int i = 0; i < src.size(); i++) {
			if (src.get(i) instanceof List) {
				// Object[] obj = new Object[((List)src.get(i)).size()];
				List list = new ArrayList();
				// Object[] obj = new Object[((List)src.get(i)).size()];
				// System.arraycopy(((List)src.get(i)).toArray(),0,obj,0,((List)src.get(i)).size());
				arraycopy((List) src.get(i), list);
				// dest.add(Collections.synchronizedList(Arrays.asList(obj)));
				dest.add(list);
			} else {
				dest.add(src.get(i));
			}
		}
	}

	public static final List<String> getNotNull(String[] arr) {
		List<String> list = new ArrayList<String>();
		for (String str : arr) {
			if (!StringUtils.isEmpty(str)) {
				list.add(str);
			}
		}
		return list;
	}

	/**
	 * 清空list
	 * 
	 * @param list
	 *          List
	 * @throws UnsupportedOperationException
	 */
	public static void clear(List list)
			throws UnsupportedOperationException {
		if (list == null || list.isEmpty()) {
			throw new UnsupportedOperationException("List is null or empty!");
		} else {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof List) {
					clear((List) list.get(i));
				}
			}
			list.clear();
		}
	}

	/**
	 * 	
	 * <table border=1 width=120>
	 * <tr>
	 * <td bgcolor=#CCCCCC>1</td>
	 * <td bgcolor=#CCCCCC>1</td>
	 * <td bgcolor=#CCCCCC>1</td>
	 * <td>1</td>
	 * </tr>
	 * <tr>
	 * <td bgcolor=#CCCCCC>1</td>
	 * <td bgcolor=#CCCCCC>1</td>
	 * <td bgcolor=#CCCCCC>1</td>
	 * <td>1</td>
	 * </tr>
	 * <tr>
	 * <td bgcolor=#CCCCCC>1</td>
	 * <td bgcolor=#CCCCCC>1</td>
	 * <td bgcolor=#CCCCCC>1</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 * 
	 * @param list
	 *          List
	 * @param startIndex
	 *          int
	 * @param indexLen
	 *          int
	 * @return List
	 */
	public static List getNewListSpecifyAbscissa(List list,
			int startIndex, int indexLen) {
		return getNewListSpecifyAll(list, startIndex, indexLen, 0, list.size());
	}

	/**
	 * 
	 * <table border=1 width=120>
	 * <tr>
	 * <td bgcolor=#CCCCCC>1</td>
	 * <td bgcolor=#CCCCCC>1</td>
	 * <td bgcolor=#CCCCCC>1</td>
	 * <td>1</td>
	 * </tr>
	 * <tr>
	 * <td bgcolor=#CCCCCC>1</td>
	 * <td bgcolor=#CCCCCC>1</td>
	 * <td bgcolor=#CCCCCC>1</td>
	 * <td>1</td>
	 * </tr>
	 * <tr>
	 * <td>1</td>
	 * <td>1</td>
	 * <td>1</td>
	 * <td>1</td>
	 * </tr>
	 * </table>
	 * 
	 * @param list
	 *          List
	 * @param startIndex
	 *          int
	 * @param indexLen
	 *          int
	 * @param startRow
	 *          int
	 * @param rowLen
	 *          int
	 * @return List
	 */
	public static List getNewListSpecifyAll(List list,
			int startIndex, int indexLen, int startRow, int rowLen) {
		List retList = new ArrayList();

		if (list == null || list.isEmpty()) {
			throw new NullPointerException(
					"java.util.List list is null or empty!");
		}
		if (!(list.get(0) instanceof List)) {
			throw new RuntimeException(
					"getNewListSpecifyAll(java.util.List list,int startIndex,int indexLen,int startRow,int rowLen) Exception: error occurs  !");
		} else if (startIndex < 0
				|| (startIndex + indexLen) > ((List) list.get(0)).size()) {
			StringBuffer sb = new StringBuffer(
					"getNewListSpecifyAll(java.util.List list,int startIndex,int indexLen,int startRow,int rowLen) Exception:startIndex ");
			sb.append(startIndex).append(",indexLen ").append(indexLen)
					.append(", error occurs !");
			throw new RuntimeException(sb.toString());
		} else if (startRow < 0 || (startRow + rowLen) > list.size()) {
			StringBuffer sb = new StringBuffer(
					"getNewListSpecifyAll(java.util.List list,int startIndex,int indexLen,int startRow,int rowLen) Exception:startIndex ");
			sb.append(startIndex).append(",indexLen ").append(indexLen)
					.append(", error occurs !");
			throw new RuntimeException(sb.toString());
		}
		for (int i = startRow; i < (startRow + rowLen); i++) {
			List temp = new ArrayList();
			for (int j = startIndex; j < (startIndex + indexLen); j++) {
				temp.add(((List) list.get(i)).get(j));
			}
			retList.add(temp);
		}
		return retList;
	}

	/**
	 * �o��S�����_�檺�s�Ʋ�
	 * 
	 * @param list
	 *          List
	 * @throws Exception
	 * @return List
	 */
	public static <T> List<T> getDistinctList(List<T> list) {
		List<T> retList = new ArrayList<T>();
		for (int i = 0; i < list.size(); i++) {
			// 取出List中的第i個變量,應該是一個Obj才對
			// List temp = (List) list.get(i);
			T temp = list.get(i);
			if (!retList.contains(temp)) {
				retList.add(temp);
			}
		}
		return retList;
	}

	/**
	 * sum filed value than the field was specialed
	 * 
	 * @param table
	 *          ArrayTable
	 * @param fieldName
	 *          String
	 * @return double
	 * @throws Exception
	 */
	// public static double sumSpeicalFiledValue(ArrayTable table,String
	// fieldName) throws Exception{
	// double sum = 0;
	// if(table.size()>0){
	// boolean beforeFirst = false;
	// boolean afterLast = false;
	// int cursor = -1;
	// if (table.isBeforeFirst()) {
	// beforeFirst = true;
	// }else if (table.isAfterLast()) {
	// afterLast = true;
	// }else{
	// cursor = table.getRow();
	// }
	// table.beforeFirst();
	// while(table.next()){
	// sum += table.getDouble(fieldName);
	// }
	// if(beforeFirst){
	// table.beforeFirst();
	// }else if(afterLast){
	// table.afterLast();
	// }else{
	// table.absolute(cursor);
	// }
	// }
	// return sum;
	// }

	/**
	 * return two list minus , so as one - another
	 * 
	 * @param one
	 *          List
	 * @param another
	 *          List
	 * @return List
	 * @throws Exception
	 */
	public static <T> List<T> getTwoListMinus(List<T> one, List<T> another) {
		if(null==another||another.isEmpty())
			return one;
		List<T> retList = new ArrayList<T>();
		for (int i = 0; i < one.size(); i++) {
			if (!another.contains(one.get(i))) {
				retList.add(one.get(i));
			}
		}
		return retList;
	}

	/**
	 * return two list intersect , so as one && another
	 * 
	 * @param one
	 *          List
	 * @param another
	 *          List
	 * @return List
	 * @throws Exception
	 */
	public static <T> List<T> intersectTwoList(List<T> one, List<T> another) {
		List<T> retList = new ArrayList<T>();
		for (int i = 0; i < one.size(); i++) {
			if (another.contains(one.get(i))) {
				retList.add(one.get(i));
			}
		}
		return retList;
	}

	/**
	 * if lists was intersected return true, otherwise return false.
	 * 
	 * @param one
	 *          List
	 * @param another
	 *          List
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean isIntersectLists(List one, List another) {
		boolean ret = false;
		for (int i = 0; i < one.size(); i++) {
			if (another.contains(one.get(i))) {
				ret = true;
				break;
			}
		}
		return ret;
	}

	/**
	 * return list size.
	 * 
	 * @param list
	 *          List
	 * @return int
	 */
	public static int getSize(List list) {
		return list.size();
	}

	public static int getSize(Collection c) {
		return c.size();
	}

	/**
	 * 
	 * Returns true if this collection contains the specified element. More
	 * formally, returns true if and only if this collection contains at least one
	 * element e such that (o==null ? e==null : o.equals(e)).
	 * 
	 * @param collection
	 *          collection
	 * @param obj
	 *          element whose presence in this collection is to be tested.
	 * 
	 * @return
	 */
	public static boolean contains(Object collectionObject, Object obj) {

		if (collectionObject == null || obj == null) {
			return false;
		}

		if (collectionObject instanceof Collection) {
			Collection collection = (Collection) collectionObject;

			if (collection.isEmpty()) {
				return false;
			}
			return collection.contains(obj);
		}

		if (collectionObject.getClass().isArray()) {
			Object[] array = (Object[]) collectionObject;
			if (array.length == 0) {
				return false;
			}
			return ArrayUtils.contains(array, obj);
		}

		return false;
	}

	public static List dropSameValue(List list) {
		return new ArrayList(new HashSet(list));
	}

	public static List getPropertyList(List list, String propertyName)
			throws Exception {
		List retList = Collections.synchronizedList(new ArrayList());
		for (Object obj : list) {
			Object o = BeanUtils.getProperty(obj, propertyName);
			retList.add(o);
		}
		return retList;
	}

	/**
	 * 剔除重複的Object,並按照原先的順序
	 * 
	 * @param list
	 * @return
	 */
	public static <T> List<T> removeDuplicate(List<T> list) {
		List<T> newList = new ArrayList<T>();
		for (T t : list) {
			if (t != null && !newList.contains(t)) {
				newList.add(t);
			}
		}
		return newList;
	}

	/*public static final List<String> splitJsonString(String jsonString) {
		List ret = null;
		if (StringUtils.isBlank(jsonString)) {
			ret = new ArrayList();
		} else if (StringUtils.startEndWithSquareBracket(jsonString)) {
			ret = JacksonUtils.readList(jsonString);
		} else {
			ret = Arrays.asList(StringUtils.split(jsonString, CharConstants.COMMA));
		}
		return ret;
	}*/

	/**
	 * 將記錄集中的資料封裝成list，每筆資料默認以數組方式封裝
	 * 
	 * @param rs
	 * @return
	 * @throws Exception
	 */
//	public final static List<Object> getList(ResultSet rs) throws Exception {
//		return getList(rs, false);
//	}

	/**
	 * 將記錄集中的資料封裝成list，每筆資料默認以數組方式封裝, transferMap=true時已map方式封裝
	 * 
	 * @param rs
	 * @return
	 * @throws Exception
	 */
//	public final static List<Object> getList(ResultSet rs, boolean transferMap)
//			throws Exception {
//		DataList<Object> dataList = new DataList<Object>();
//		ResultSetMetaData rsMetaData = rs.getMetaData();
//		dataList.setResultSetMeta(getResultSetMeta(rsMetaData));
//		String[] columnNames = getColumns(rsMetaData);
//		while (rs.next()) {
//			Object row = populateRow(rs, columnNames, transferMap);
//			dataList.add(row);
//		}
//		return dataList;
//	}

//	public final static ResultSetMeta getResultSetMeta(
//			ResultSetMetaData rsMetaData) throws Exception {
//		if (null == rsMetaData)
//			return null;
//		List<ColumnMeta> retList = new ArrayList<>();
//		int colCount = rsMetaData.getColumnCount();
//		for (int i = 0; i < colCount; i++) {
//			int index = (i + 1);
//			retList.add(new ColumnMeta(i, rsMetaData.getColumnName(index), rsMetaData
//					.getColumnType(index), rsMetaData.getColumnTypeName(index),
//					rsMetaData.getColumnDisplaySize(index), rsMetaData
//							.getPrecision(index), rsMetaData.getScale(index)));
//		}
//		return ColumnMetaUtils.getResultSetMeta(retList);
//	}

//	private static Object populateRow(ResultSet rs, String[] columnNames,
//			boolean transferMap) throws SQLException {
//		if (transferMap) {
//			return populateMap(rs, columnNames);
//		} else {
//			return populateObjectArray(rs, columnNames);
//		}
//	}
//
//	private static Map<String, Object> populateMap(ResultSet rs,
//			String[] columnNames) throws SQLException {
//		Map<String, Object> row = new HashMap<String, Object>();
//		for (int i = 0; i < columnNames.length; i++) {
//			Object obj = null;
//			try {
//				obj = transfer(rs, i + 1);
//				// log.info(metaData[i] + "'s value : " + obj);
//			} catch (Exception _ex) {
//				obj = rs.getObject(i + 1);
//			} catch (AbstractMethodError _ex) {
//				obj = rs.getObject(i + 1);
//			}
//			row.put(columnNames[i], obj);
//			// log.info("1. value : {} " , obj);
//		}
//		return row;
//	}
//
//	private static Object[] populateObjectArray(ResultSet rs, String[] columnNames)
//			throws SQLException {
//		Object[] row = new Object[columnNames.length];
//		for (int i = 0; i < columnNames.length; i++) {
//			Object obj = null;
//			try {
//				obj = transfer(rs, i + 1);
//				// log.info("//2 {}",obj);
//			} catch (Exception _ex) {
//				obj = rs.getObject(i + 1);
//			} catch (AbstractMethodError _ex) {
//				obj = rs.getObject(i + 1);
//			}
//			row[i] = obj;
//			// break;
//		}
//		return row;
//	}
//
//	private final static Object transfer(ResultSet rs, int index)
//			throws SQLException {
//		Object obj = rs.getObject(index);
//		if (obj == null)
//			return "";
//		// log.info("{},{}",obj.getClass(),Date.class.isAssignableFrom(obj.getClass()));
//		if (DATE_TYPE.contains(obj.getClass().getName())) {
//			obj = rs.getDate(index);
//		} else if (TIME_TYPE.contains(obj.getClass().getName())) {
//			obj = rs.getTimestamp(index);
//		} else if (Clob.class.isAssignableFrom(obj.getClass())) {
//			obj = StringUtils.getClobValue((Clob) obj);
//			// log.info("......{}",obj);
//		} else if (Blob.class.isAssignableFrom(obj.getClass())) {
//			obj = (Blob) obj;
//			// log.info("......{}",obj);
//		}
//		return obj;
//	}
//
//	private static String[] getColumns(ResultSetMetaData rsMetaData)
//			throws SQLException {
//		int colCount = rsMetaData.getColumnCount();
//		String[] metaData = new String[colCount];
//		for (int i = 0; i < colCount; i++) {
//			metaData[i] = rsMetaData.getColumnName(i + 1);
//		}
//		return metaData;
//	}

}
