package com.upstyle.upstyle.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QComment is a Querydsl query type for Comment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QComment extends EntityPathBase<Comment> {

    private static final long serialVersionUID = -1233886446L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QComment comment = new QComment("comment");

    public final com.upstyle.upstyle.domain.common.QBaseEntity _super = new com.upstyle.upstyle.domain.common.QBaseEntity(this);

    public final StringPath body = createString("body");

    public final ListPath<com.upstyle.upstyle.domain.mapping.CommentLike, com.upstyle.upstyle.domain.mapping.QCommentLike> commentLikeList = this.<com.upstyle.upstyle.domain.mapping.CommentLike, com.upstyle.upstyle.domain.mapping.QCommentLike>createList("commentLikeList", com.upstyle.upstyle.domain.mapping.CommentLike.class, com.upstyle.upstyle.domain.mapping.QCommentLike.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QOotd ootd;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final QUser user;

    public QComment(String variable) {
        this(Comment.class, forVariable(variable), INITS);
    }

    public QComment(Path<? extends Comment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QComment(PathMetadata metadata, PathInits inits) {
        this(Comment.class, metadata, inits);
    }

    public QComment(Class<? extends Comment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ootd = inits.isInitialized("ootd") ? new QOotd(forProperty("ootd"), inits.get("ootd")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

