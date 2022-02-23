CREATE TABLE IF NOT EXISTS `trick-email`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(68) NOT NULL,
  `nickname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_user_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `trick-email`.`email` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `subject` VARCHAR(45) NOT NULL,
  `body` VARCHAR(1000) NULL,
  `assignment` VARCHAR(255) NULL,
  `label` VARCHAR(255) NULL,
  `status` INT NOT NULL,
  `iduser` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_email_user`
    FOREIGN KEY (`iduser`)
    REFERENCES `trick-email`.`user` (`id`)
)
ENGINE = InnoDB;
