create table yupi.user
(
    id           bigint unsigned zerofill auto_increment comment 'id'
        primary key,
    userName     varchar(256)                       null comment '用户昵称',
    userAccount  varchar(256)                       null comment '账号',
    role         int      default 0                 not null comment '用户角色 0 - 普通用户 1 - 管理员',
    avatarUrl    varchar(1024)                      null comment '用户头像',
    gender       tinyint                            null comment '性别',
    userPassword varchar(512)                       not null comment '密码',
    phone        varchar(128)                       null comment '电话',
    email        varchar(512)                       null comment '邮箱',
    userStatus   int      default 0                 not null comment '状态 0 - 正常',
    createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    isDelete     tinyint  default 0                 not null comment '是否删除'
)
    comment '用户' collate = utf8mb4_general_ci;

create table tag
(
    id           bigint unsigned zerofill auto_increment comment 'id'
        primary key,
    tagName     varchar(256)                       null comment '标签名称',
    userId      bigint                             null comment '用户id',
    parentId    bigint                             null comment '父标签id',
    isParent    tinyint  default 0                 not null comment '是否为父标签 0 - 否 1 - 是',
    createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    isDelete     tinyint  default 0                 not null comment '是否删除'
)
    comment '标签' collate = utf8mb4_general_ci;