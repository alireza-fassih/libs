package ir.fassih.datamanagement.manager;

import ir.fassih.datamanagement.model.SearchMethod;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Date;
import java.util.Map;

@Slf4j
@AllArgsConstructor
public class SearchSpecification<T> implements Specification<T> {

    private final Map<String, String> filters;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate[] predicates = filters.entrySet().stream()
                .map(entry -> buildPredicate(entry.getKey(), entry.getValue(), root, cb))
                .filter(p -> p != null)
                .toArray(Predicate[]::new);
        return cb.and(predicates);
    }


    private Predicate buildPredicate(String key, String value, Root<T> root, CriteriaBuilder cb) {
        KeyModel keyModel = extractKey(key);
        if (keyModel == null) {
            return null;
        }

        SearchMethod method = keyModel.getMethod();
        Path<?> path = extractPath(root, keyModel.getFieldName());
        if( method == SearchMethod.EQ ) {
           return createEqualPredicate(path, value, cb);
        } else if ( method == SearchMethod.LIKE ) {
            return createLikePredicate(path, value, cb);
        }

        return null;
    }

    private Predicate createEqualPredicate(Path<?> path, String value, CriteriaBuilder cb) {
        return cb.equal(path, convertValue(path.getJavaType(), value));
    }


    private Predicate createLikePredicate(Path<?> path, String value, CriteriaBuilder cb) {
        if (String.class.isAssignableFrom(path.getJavaType())) {
            return cb.like((Path<String>)path, "%" + value + "%");
        } else {
            log.warn("cannot create like query on none string {}", path);
            return null;
        }
    }


    private Path<?> extractPath(Root<T> root, String fieldName) {
        Path<?> element = null;
        if (fieldName.contains(".")) {
            for (String path : fieldName.split("\\.")) {
                element = (element == null ? root.get(path) : element.get(path));
            }
        } else {
            element = root.get(fieldName);
        }
        return element;
    }

    private Object convertValue(Class<?> javaType, String value) {
        if (Date.class.isAssignableFrom(javaType)) {
            return new Date(Long.parseLong(value));
        } else if (Long.class.isAssignableFrom(javaType)) {
            return Long.parseLong(value);
        } else if (Boolean.class.isAssignableFrom(javaType)) {
            return Boolean.parseBoolean(value);
        }
        return value;
    }

    private KeyModel extractKey(String key) {
        for (SearchMethod method : SearchMethod.values()) {
            String prefix = new StringBuilder().append(method.name()).append(":").toString();
            if (key.startsWith(prefix)) {
                return new KeyModel(method, key.replace(prefix, ""));
            }
        }
        log.warn("ignoring unknown search method on {}", key);
        return null;
    }

    @Value
    private static class KeyModel {
        private SearchMethod method;
        private String fieldName;
    }


}
