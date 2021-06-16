# CALL filterCampaignsCount(35, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

DROP procedure if exists filterCampaignsCount;
create
    definer = travelbook@`%` procedure filterCampaignsCount(IN countryId int,
                                                            IN stateId int, IN cityId int, IN place varchar(255),
                                                            IN minBudget int, IN maxBudget int, IN userId mediumtext,
                                                            IN campaignsStatusId int, IN campaignsApprovalStatusId int,
                                                            IN createdDateOrderBy varchar(50))
BEGIN
    DECLARE whereClause varchar(255);
    DECLARE orderBy varchar(255);
    DECLARE countryJoinClause varchar(255);
    DECLARE countrySelect varchar(255);
    DECLARE stateJoinClause varchar(255);
    DECLARE stateSelect varchar(255);
    DECLARE cityJoinClause varchar(255);
    DECLARE citySelect varchar(255);
    SET whereClause = ' 1=1';
    SET countryJoinClause = '';
    SET countrySelect = '';
    SET stateJoinClause = '';
    SET stateSelect = '';
    SET cityJoinClause = '';
    SET citySelect = '';

    IF (place IS NOT NULL) THEN
        SET whereClause = CONCAT(whereClause, ' AND place LIKE \'', place, '\'');
    END IF;

    IF (minBudget IS NOT NULL) THEN
        SET whereClause = CONCAT(whereClause, ' AND budgets >= ', minBudget);
    END IF;

    IF (maxBudget IS NOT NULL) THEN
        SET whereClause = CONCAT(whereClause, ' AND budgets <= ', maxBudget);
    END IF;

    IF (userId IS NOT NULL) THEN
        SET whereClause = CONCAT(whereClause, ' AND user_id = ', userId);
    END IF;

    IF (campaignsStatusId IS NOT NULL) THEN
        SET whereClause = CONCAT(whereClause, ' AND campaigns_status_id = ', campaignsStatusId);
    END IF;

    IF (campaignsApprovalStatusId IS NOT NULL) THEN
        SET whereClause = CONCAT(whereClause, ' AND campaigns_approval_status_id = ', campaignsApprovalStatusId);
    END IF;

    IF (createdDateOrderBy IS NOT NULL) THEN
        SET orderBy = CONCAT(' ORDER BY created_date ', createdDateOrderBy);
    ELSE
        SET orderBy = ' ORDER BY created_date';
    END IF;

    IF (countryId IS NOT NULL) THEN
        SET countryJoinClause = ' INNER JOIN countries cn ON ca.countries_id = cn.id ';
        SET whereClause = CONCAT(whereClause, ' AND ca.countries_id = ', countryId);
    END IF;

    IF (stateId IS NOT NULL) THEN
        SET stateJoinClause = ' INNER JOIN states st ON ca.states_id = st.id ';
        SET whereClause = CONCAT(whereClause, ' AND ca.states_id ', stateId);
    END IF;

    IF (cityId IS NOT NULL) THEN
        SET cityJoinClause = ' INNER JOIN cities ci ON ca.cities_id = ci.id ';
        SET whereClause = CONCAT(whereClause, ' AND ca.cities_id ', cityId);
    END IF;

    SET @sql = CONCAT(
            'SELECT
                COUNT(*)
            FROM campaigns ca
                     LEFT JOIN countries cn ON ca.countries_id = cn.id
                     LEFT JOIN states st ON ca.states_id = st.id
                     LEFT JOIN cities ci ON ca.cities_id = ci.id
            WHERE ', whereClause);
#     SELECT whereClause, countryJoinClause, stateJoinClause, cityJoinClause, @sql;
    PREPARE stmt FROM @sql;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END;

