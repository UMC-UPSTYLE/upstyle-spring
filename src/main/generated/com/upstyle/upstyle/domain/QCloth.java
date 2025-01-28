package com.upstyle.upstyle.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCloth is a Querydsl query type for Cloth
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCloth extends EntityPathBase<Cloth> {

    private static final long serialVersionUID = -1444945011L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCloth cloth = new QCloth("cloth");

    public final com.upstyle.upstyle.domain.common.QBaseEntity _super = new com.upstyle.upstyle.domain.common.QBaseEntity(this);

    public final StringPath additionalInfo = createString("additionalInfo");

    public final QClothCategory category;

    public final QClothColor color;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final QClothFit fit;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final QClothKind kind;

    public final ListPath<com.upstyle.upstyle.domain.mapping.OotdCloth, com.upstyle.upstyle.domain.mapping.QOotdCloth> ootdClothList = this.<com.upstyle.upstyle.domain.mapping.OotdCloth, com.upstyle.upstyle.domain.mapping.QOotdCloth>createList("ootdClothList", com.upstyle.upstyle.domain.mapping.OotdCloth.class, com.upstyle.upstyle.domain.mapping.QOotdCloth.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final QUser user;

    public QCloth(String variable) {
        this(Cloth.class, forVariable(variable), INITS);
    }

    public QCloth(Path<? extends Cloth> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCloth(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCloth(PathMetadata metadata, PathInits inits) {
        this(Cloth.class, metadata, inits);
    }

    public QCloth(Class<? extends Cloth> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QClothCategory(forProperty("category"), inits.get("category")) : null;
        this.color = inits.isInitialized("color") ? new QClothColor(forProperty("color")) : null;
        this.fit = inits.isInitialized("fit") ? new QClothFit(forProperty("fit")) : null;
        this.kind = inits.isInitialized("kind") ? new QClothKind(forProperty("kind")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

