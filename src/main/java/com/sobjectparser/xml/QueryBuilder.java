package com.sobjectparser.xml;

import java.util.List;
import java.util.Map;

public class QueryBuilder {
	public static String buildQuery(String object, Map<String, List<String>> sourceXML){
		String query = "select ";
		List<String> fields = sourceXML.get("fullName");
		for(String field:fields){
			query += field + ", ";
		}
		query = query.substring(0, query.length()-2);
		query += " from " + object;
		return query;
	}
}
