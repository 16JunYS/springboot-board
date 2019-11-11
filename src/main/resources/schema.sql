CREATE TABLE IF NOT EXISTS `user` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(128) NOT NULL,
    `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `board` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(128) NOT NULL,
    `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `post` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT UNSIGNED NOT NULL,
    `board_id` BIGINT UNSIGNED NOT NULL,
    `no` INT UNSIGNED NOT NULL,
    `title` VARCHAR(128) NOT NULL,
    `content` TEXT NOT NULL,
    `views` INT UNSIGNED NOT NULL DEFAULT 0,
    `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES user(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`board_id`) REFERENCES board(`id`) ON DELETE CASCADE,
    INDEX idx_post (`board_id`, `no`)
);

CREATE TABLE IF NOT EXISTS comment (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT UNSIGNED NOT NULL,
    `post_id` BIGINT UNSIGNED NOT NULL,
    `group` INT UNSIGNED NOT NULL,
    `sequence` INT UNSIGNED NOT NULL,
    `level` INT UNSIGNED NOT NULL,
    `content` VARCHAR(128) NOT NULL,
    `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES user(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`post_id`) REFERENCES post(`id`) ON DELETE CASCADE,
    INDEX idx_comment (`post_id`, `group`, `sequence`)
);