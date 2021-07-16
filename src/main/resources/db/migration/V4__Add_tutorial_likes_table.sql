create table tutorial_likes (
    tutorial_id bigint not null references tutorials,
    user_id bigint not null references users,
    primary key (tutorial_id, user_id)
)

