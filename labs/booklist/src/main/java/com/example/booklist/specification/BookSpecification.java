package com.example.booklist.specification;

import com.example.booklist.model.Book;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<Book> byTitle(String title) {
        return ((root, query, criteriaBuilder) -> {
            if (title == null || title.trim().isBlank()) {
                return null;
            }
            return criteriaBuilder.equal(root.get("title"), title);
        });
    }

    public static Specification<Book> byTitleContains(String title) {
        return ((root, query, criteriaBuilder) -> {
            if (title == null || title.trim().isBlank()) {
                return null;
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.trim().toLowerCase() + "%");
        });
    }

    public static Specification<Book> byYearGreaterThan(Integer year) {
        return ((root, query, criteriaBuilder) -> {
            if (year == null) {
                return null;
            }
            return criteriaBuilder.greaterThan(root.get("year"), year);
        });
    }

    public static Specification<Book> byYearLessThan(Integer year) {
        return ((root, query, criteriaBuilder) -> {
            if (year == null) {
                return null;
            }
            return criteriaBuilder.lessThan(root.get("year"), year);
        });
    }

    public static Specification<Book> byYearBetween(Integer start, Integer end) {
        return ((root, query, criteriaBuilder) -> {
            if (start == null || end == null) {
                return null;
            }
            return criteriaBuilder.between(root.get("year"), start, end);
        });
    }

    public static Specification<Book> byInStock(Boolean inStock) {
        return ((root, query, criteriaBuilder) -> {
            if (inStock == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("inStock"), inStock);
        });
    }

    public static Specification<Book> byGenre(String genre) {
        return (root, query, criteriaBuilder) -> {
            if (genre == null || genre.trim().isBlank()) {
                return null;
            }
            return criteriaBuilder.equal(root.get("genre").get("name"), genre);
        };
    }

    public static Specification<Book> byAuthor(String author) {
        return (root, query, criteriaBuilder) -> {
            if (author == null || author.trim().isBlank()) {
                return null;
            }

            assert query != null;
            query.distinct(true);
            Join<Object, Object> authorsJoin = root.join("authors");

            return criteriaBuilder.like(criteriaBuilder.lower(authorsJoin.get("name")), "%" + author.toLowerCase() + "%");
        };
    }

    public static Specification<Book> byPublisher(String publisher) {
        return (root, query, criteriaBuilder) -> {
            if (publisher == null || publisher.trim().isBlank()) {
                return null;
            }

            assert query != null;
            query.distinct(true);
            Join<Object, Object> publishersJoin = root.join("publishers");

            return criteriaBuilder.equal(publishersJoin.get("name"), publisher);
        };
    }

    public static Specification<Book> byPublisherNameContains(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.trim().isBlank()) {
                return null;
            }
            assert query != null;
            query.distinct(true);
            Join<Object, Object> publishersJoin = root.join("publishers");
            return criteriaBuilder.like(criteriaBuilder.lower(publishersJoin.get("name")), "%" + name.toLowerCase() + "%");
        };
    }

    public static Specification<Book> byPriceLessThan(Double price) {
        return (root, query, criteriaBuilder) -> {
            if (price == null) {
                return null;
            }
            return criteriaBuilder.lessThan(root.get("price"), price);
        };
    }

}
