CREATE TABLE `tasks` (
 `id`         BIGINT         AUTO_INCREMENT,
 `body`       VARCHAR(255)   NOT NULL,
 `deadline`   VARCHAR(255)   NOT NUll,
 `details`    VARCHAR(255)   NULL,
 `create_at`  TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
 `update_at`  TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY  (`id`)
);