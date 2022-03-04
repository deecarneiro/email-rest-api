CREATE TABLE IF NOT EXISTS `trick-email`.`user_token` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `token` VARCHAR(1000) NULL,
  `iduser` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_token_user`
    FOREIGN KEY (`iduser`)
    REFERENCES `trick-email`.`user` (`id`)
)
ENGINE = InnoDB;
