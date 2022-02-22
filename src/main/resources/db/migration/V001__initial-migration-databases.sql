CREATE TABLE IF NOT EXISTS `trick-email`.`user` (
  `id` INT NOT NULL,
  `name` VARCHAR(68) NOT NULL,
  `nickname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_user_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `trick-email`.`email` (
  `id` INT NOT NULL,
  `subject` VARCHAR(45) NOT NULL,
  `body` VARCHAR(1000) NULL,
  `assignment` VARCHAR(255) NULL,
  `label` VARCHAR(255) NULL,
  `status` INT NOT NULL,
  `id_user` INT NOT NULL,
  PRIMARY KEY (`id`, `id_user`),
  CONSTRAINT `fk_email_user1`
    FOREIGN KEY (`id_user`)
    REFERENCES `trick-email`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
