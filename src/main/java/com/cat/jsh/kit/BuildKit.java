package com.cat.jsh.kit;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.cat.jsh.kit.BeanKit.getFieldList;

public class BuildKit {

	public static void build(Class<?> clazz, String id, String resource) throws Exception {
		Path path;
		if (resource == null || resource.isEmpty()) {
			path = IOKit.createPath("mapper/" + clazz.getSimpleName() + ".xml");
		} else {
			path = Paths.get(resource);
		}

		IOKit.write(path, content(clazz, id).getBytes());
	}

	public static String content(Class<?> clazz, String key) {
		String className = clazz.getSimpleName();

		String classPath = clazz.getName();

		//namespace
		String namespace = classPath;
		Pattern r = Pattern.compile("(.\\w+.)" + className);
		Matcher matcher = r.matcher(classPath);
		if (matcher.find()) {
			String replace = matcher.group(1);
			//entity->dao,+Dao
			namespace = classPath.replace(replace, ".dao.") + "Dao";
		}

		//entity
		String entity = className.toLowerCase();
		//id
		String id = key == null || key.isEmpty() ? "id" : key;
		//columns
		String columns = BeanKit.getFields(clazz);

		//saveValue
		List<String> columnList = getFieldList(clazz);
		List<String> list = new ArrayList<>(columnList.size());
		for (int i = 0; i < columnList.size(); i++) {
			list.add(i, "#{" + columnList.get(i) + "}");
		}
		String saveValue = Arrays.toString(list.toArray());
		saveValue = saveValue.substring(1, saveValue.length() - 1);

		//saveValues
		for (int i = 0; i < columnList.size(); i++) {
			list.set(i, "#{entity." + columnList.get(i) + "}");
		}
		String saveValues = Arrays.toString(list.toArray());
		saveValues = saveValues.substring(1, saveValues.length() - 1);

		//updateValues
		list.clear();
		list.addAll(columnList.stream().filter(s -> !s.equals(id)).map(s -> s + " = #{" + s + "}").collect(Collectors.toList()));
		String updateValues = Arrays.toString(list.toArray());
		updateValues = updateValues.substring(1, updateValues.length() - 1);

		//result
		String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n" +
				"<mapper namespace=\"" + namespace + "\">\n" +
				"    <sql id=\"entity\">" + entity + "</sql>\n" +
				"    <sql id=\"id\">" + id + "</sql>\n" +
				"    <sql id=\"columns\">" + columns + "</sql>\n" +
				"    <sql id=\"page\">\n" +
				"        <if test=\"offset >= 0 and limit > 0\">\n" +
				"            limit #{offset}, #{limit}\n" +
				"        </if>\n" +
				"    </sql>\n" +
				"    <sql id=\"sort\">\n" +
				"        <if test=\"sort != null and !sort.isEmpty() and order != null and !order.isEmpty()\">\n" +
				"            ORDER BY ${sort} ${order}\n" +
				"        </if>\n" +
				"    </sql>\n" +
				"\n" +
				"    <insert id=\"save\" keyProperty=\"" + id + "\">\n" +
				"        INSERT INTO\n" +
				"        <include refid=\"entity\"/>\n" +
				"        (<include refid=\"columns\"/>)\n" +
				"        VALUES\n" +
				"        (" + saveValue + ")\n" +
				"    </insert>\n" +
				"    <insert id=\"saves\" keyProperty=\"" + id + "\">\n" +
				"        INSERT INTO\n" +
				"        <include refid=\"entity\"/>\n" +
				"        (<include refid=\"columns\"/>)\n" +
				"        VALUES\n" +
				"        <foreach collection=\"list\" item=\"entity\" separator=\",\">\n" +
				"            (" + saveValues + ")\n" +
				"        </foreach>\n" +
				"    </insert>\n" +
				"    <delete id=\"deleteById\">\n" +
				"        DELETE FROM\n" +
				"        <include refid=\"entity\"/>\n" +
				"        WHERE\n" +
				"        <include refid=\"id\"/>\n" +
				"        = #{" + id + "}\n" +
				"    </delete>\n" +
				"    <delete id=\"deleteByEntity\">\n" +
				"        DELETE FROM\n" +
				"        <include refid=\"entity\"/>\n" +
				"        WHERE\n" +
				"        <include refid=\"id\"/>\n" +
				"        = #{" + id + "}\n" +
				"    </delete>\n" +
				"    <delete id=\"deleteByIds\">\n" +
				"        DELETE FROM\n" +
				"        <include refid=\"entity\"/>\n" +
				"        WHERE\n" +
				"        <include refid=\"id\"/>" +
				"        IN" +
				"        <foreach collection=\"array\" item=\"id\" open=\"(\" separator=\",\" close=\")\">\n" +
				"            #{id}\n" +
				"        </foreach>\n" +
				"    </delete>\n" +
				"    <delete id=\"deleteByEntities\">\n" +
				"        DELETE FROM\n" +
				"        <include refid=\"entity\"/>\n" +
				"        WHERE\n" +
				"        <include refid=\"id\"/>" +
				"        IN" +
				"        <foreach collection=\"list\" item=\"entity\" open=\"(\" separator=\",\" close=\")\">\n" +
				"            #{entity." + id + "}\n" +
				"        </foreach>\n" +
				"    </delete>\n" +
				"    <update id=\"update\">\n" +
				"        UPDATE\n" +
				"        <include refid=\"entity\"/>\n" +
				"        SET\n" +
				"        " + updateValues + "\n" +
				"        WHERE\n" +
				"        <include refid=\"id\"/>\n" +
				"        = #{" + id + "}\n" +
				"    </update>\n" +
				"    <select id=\"findById\" resultType=\"" + entity + "\">\n" +
				"        SELECT * FROM\n" +
				"        <include refid=\"entity\"/>\n" +
				"        WHERE\n" +
				"        <include refid=\"id\"/>\n" +
				"        = #{" + id + "}\n" +
				"    </select>\n" +
				"    <select id=\"findList\" resultType=\"" + entity + "\">\n" +
				"        SELECT * FROM\n" +
				"        <include refid=\"entity\"/>\n" +
				"        <include refid=\"search\"/>\n" +
				"        <include refid=\"sort\"/>\n" +
				"        <include refid=\"page\"/>\n" +
				"    </select>\n" +
				"    <select id=\"count\" resultType=\"int\">\n" +
				"        SELECT COUNT(*) FROM\n" +
				"        <include refid=\"entity\"/>\n" +
				"        <include refid=\"search\"/>\n" +
				"    </select>\n" +
				"\n" +
				"    <sql id=\"search\">\n" +
				"    </sql>\n" +
				"</mapper>";

		return content;
	}
}
