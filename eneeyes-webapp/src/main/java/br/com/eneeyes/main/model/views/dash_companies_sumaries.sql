CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `dash_summary_companies` AS
    SELECT 
        `x`.`NAME` AS `NAME`,
        SUM(`x`.`units`) AS `units`,
        SUM(`x`.`areas`) AS `areas`,
        SUM(`x`.`devices`) AS `devices`
    FROM
        `dash_totals_companies` `x`
    GROUP BY `x`.`NAME`