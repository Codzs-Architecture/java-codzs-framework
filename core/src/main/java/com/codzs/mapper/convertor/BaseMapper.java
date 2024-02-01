package com.codzs.mapper.convertor;

import com.codzs.mapper.annotation.*;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;

public interface BaseMapper {
    @IntToLongMapper
    default Long intToLong(Integer value)   {
        return value == null ? null : value.longValue();
    }

    @LongToIntMapper
    default Integer longToInt(Long value)   {
        return value == null ? null : value.intValue();
    }

    @StringToDateMapper
    default Date longToInt(String date)   {
        return date == null ? null : new Date(date);
    }

    @DateToStringMapper
    default String longToInt(Date date)   {
        return date == null ? null : date.toString();
    }

    @DateToTimestrampMapper
    default Long dateToTimestamp(Date date)   {
        return date == null ? null : date.getTime();
    }

    @TimestrampToDateMapper
    default Date dateToTimestamp(Long date)   {
        return date == null ? null : new Date(date);
    }

    @EnumToStringMapper
    default String enumToString(Enum type)   {
        return type == null ? null : type.toString();
    }

    @IntToBooleanMapper
    default Boolean intToBoolean(Integer value)   {
        return value == null ? null : value == 1;
    }

    @BooleanToIntMapper
    default Integer booleanToInt(Boolean value)   {
        return value == null ? null : value ? 1 : 0;
    }

    @StringToDoubleMapper
    default Double stringToDouble(String value)   {
        return value == null ? null : Double.valueOf(value);
    }

    @DoubleToStringMapper
    default String doubleToString(Double value)   {
        return value == null ? null : value.toString();
    }

    @StringToIntMapper
    default Integer stringToInt(String value)   {
        return value == null ? null : Integer.valueOf(value);
    }

    @IntToStringMapper
    default String intToString(Integer value)   {
        return value == null ? null : value.toString();
    }

    @StringToListMapper
    default List<String> stringToList(String value)   {
        return Arrays.asList(value.split(","));
    }

    @ListToStringMapper
    default String listToString(List<String> list)   {
        final StringBuilder sb = new StringBuilder();
        if (list != null) {
            list.forEach(bv -> sb.append(bv).append(","));
        }
        return sb.substring(0, sb.length() - 1);
    }

    @StringToLongMapper
    default Long stringToLong(String value)   {
        return value == null ? null : Long.valueOf(value);
    }

    @LongToStringMapper
    default String longToString(Long value)   {
        return value == null ? null : value.toString();
    }

    @StringToMapMapper
    default Map<String, Object> stringToMap(String value)   {
        Map<String, Object> map = new HashMap<>();

        if (value != null) {
            try {
                final ObjectMapper mapper = new ObjectMapper();
                map = mapper.readValue(value, new TypeReference<Map<String, Object>>() {
                });
            } catch (final JsonGenerationException e) {
                e.printStackTrace();
            } catch (final JsonMappingException e) {
                e.printStackTrace();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }

        return map;
    }

    @MapToStringMapper
    default String mapToString(Map<String, Object> map)   {
        String json = "";

        if (map != null) {
            try {
                final ObjectMapper mapper = new ObjectMapper();

                // convert map to JSON string
                json = mapper.writeValueAsString(map);

                json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
            } catch (final JsonGenerationException e) {
                e.printStackTrace();
            } catch (final JsonMappingException e) {
                e.printStackTrace();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return json;
    }
}
