package com.upstyle.upstyle.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -323163144L;

    public static final QUser user = new QUser("user");

    public final com.upstyle.upstyle.domain.common.QBaseEntity _super = new com.upstyle.upstyle.domain.common.QBaseEntity(this);

    public final ListPath<com.upstyle.upstyle.domain.mapping.ClothBookmark, com.upstyle.upstyle.domain.mapping.QClothBookmark> clothBookmarkList = this.<com.upstyle.upstyle.domain.mapping.ClothBookmark, com.upstyle.upstyle.domain.mapping.QClothBookmark>createList("clothBookmarkList", com.upstyle.upstyle.domain.mapping.ClothBookmark.class, com.upstyle.upstyle.domain.mapping.QClothBookmark.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final EnumPath<com.upstyle.upstyle.domain.enums.Gender> gender = createEnum("gender", com.upstyle.upstyle.domain.enums.Gender.class);

    public final NumberPath<Float> height = createNumber("height", Float.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final DatePath<java.time.LocalDate> inactiveDate = createDate("inactiveDate", java.time.LocalDate.class);

    public final StringPath nickname = createString("nickname");

    public final ListPath<Ootd, QOotd> ootdList = this.<Ootd, QOotd>createList("ootdList", Ootd.class, QOotd.class, PathInits.DIRECT2);

    public final ListPath<OotdRequest, QOotdRequest> ootdRequestList = this.<OotdRequest, QOotdRequest>createList("ootdRequestList", OotdRequest.class, QOotdRequest.class, PathInits.DIRECT2);

    public final ListPath<OotdResponse, QOotdResponse> ootdResponseList = this.<OotdResponse, QOotdResponse>createList("ootdResponseList", OotdResponse.class, QOotdResponse.class, PathInits.DIRECT2);

    public final StringPath password = createString("password");

    public final StringPath picture = createString("picture");

    public final NumberPath<Integer> reward = createNumber("reward", Integer.class);

    public final EnumPath<com.upstyle.upstyle.domain.enums.Role> role = createEnum("role", com.upstyle.upstyle.domain.enums.Role.class);

    public final StringPath status = createString("status");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final ListPath<Vote, QVote> voteList = this.<Vote, QVote>createList("voteList", Vote.class, QVote.class, PathInits.DIRECT2);

    public final NumberPath<Float> weight = createNumber("weight", Float.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

