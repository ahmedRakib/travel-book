CREATE TABLE IF NOT EXISTS `countries` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `short_name` varchar(3) NOT NULL,
  `name` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=249 ;

INSERT INTO travelbook.countries (id, short_name, name) VALUES (1, 'AF', 'Afghanistan');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (2, 'AL', 'Albania');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (3, 'DZ', 'Algeria');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (4, 'AS', 'American Samoa');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (5, 'AD', 'Andorra');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (6, 'AO', 'Angola');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (7, 'AI', 'Anguilla');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (8, 'AQ', 'Antarctica');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (9, 'AG', 'Antigua And Barbuda');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (10, 'AR', 'Argentina');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (11, 'AM', 'Armenia');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (12, 'AW', 'Aruba');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (13, 'AU', 'Australia');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (14, 'AT', 'Austria');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (15, 'AZ', 'Azerbaijan');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (16, 'BS', 'Bahamas The');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (17, 'BH', 'Bahrain');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (18, 'BD', 'Bangladesh');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (19, 'BB', 'Barbados');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (20, 'BY', 'Belarus');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (21, 'BE', 'Belgium');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (22, 'BZ', 'Belize');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (23, 'BJ', 'Benin');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (24, 'BM', 'Bermuda');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (25, 'BT', 'Bhutan');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (26, 'BO', 'Bolivia');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (27, 'BA', 'Bosnia and Herzegovina');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (28, 'BW', 'Botswana');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (29, 'BV', 'Bouvet Island');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (30, 'BR', 'Brazil');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (31, 'IO', 'British Indian Ocean Territory');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (32, 'BN', 'Brunei');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (33, 'BG', 'Bulgaria');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (34, 'BF', 'Burkina Faso');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (35, 'BI', 'Burundi');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (36, 'KH', 'Cambodia');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (37, 'CM', 'Cameroon');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (38, 'CA', 'Canada');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (39, 'CV', 'Cape Verde');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (40, 'KY', 'Cayman Islands');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (41, 'CF', 'Central African Republic');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (42, 'TD', 'Chad');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (43, 'CL', 'Chile');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (44, 'CN', 'China');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (45, 'CX', 'Christmas Island');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (46, 'CC', 'Cocos (Keeling) Islands');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (47, 'CO', 'Colombia');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (48, 'KM', 'Comoros');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (49, 'CG', 'Republic Of The Congo');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (50, 'CD', 'Democratic Republic Of The Congo');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (51, 'CK', 'Cook Islands');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (52, 'CR', 'Costa Rica');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (53, 'CI', 'Cote D''Ivoire (Ivory Coast)');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (54, 'HR', 'Croatia (Hrvatska)');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (55, 'CU', 'Cuba');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (56, 'CY', 'Cyprus');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (57, 'CZ', 'Czech Republic');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (58, 'DK', 'Denmark');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (59, 'DJ', 'Djibouti');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (60, 'DM', 'Dominica');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (61, 'DO', 'Dominican Republic');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (62, 'TP', 'East Timor');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (63, 'EC', 'Ecuador');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (64, 'EG', 'Egypt');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (65, 'SV', 'El Salvador');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (66, 'GQ', 'Equatorial Guinea');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (67, 'ER', 'Eritrea');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (68, 'EE', 'Estonia');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (69, 'ET', 'Ethiopia');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (70, 'XA', 'External Territories of Australia');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (71, 'FK', 'Falkland Islands');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (72, 'FO', 'Faroe Islands');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (73, 'FJ', 'Fiji Islands');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (74, 'FI', 'Finland');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (75, 'FR', 'France');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (76, 'GF', 'French Guiana');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (77, 'PF', 'French Polynesia');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (78, 'TF', 'French Southern Territories');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (79, 'GA', 'Gabon');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (80, 'GM', 'Gambia The');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (81, 'GE', 'Georgia');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (82, 'DE', 'Germany');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (83, 'GH', 'Ghana');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (84, 'GI', 'Gibraltar');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (85, 'GR', 'Greece');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (86, 'GL', 'Greenland');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (87, 'GD', 'Grenada');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (88, 'GP', 'Guadeloupe');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (89, 'GU', 'Guam');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (90, 'GT', 'Guatemala');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (91, 'XU', 'Guernsey and Alderney');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (92, 'GN', 'Guinea');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (93, 'GW', 'Guinea-Bissau');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (94, 'GY', 'Guyana');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (95, 'HT', 'Haiti');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (96, 'HM', 'Heard and McDonald Islands');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (97, 'HN', 'Honduras');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (98, 'HK', 'Hong Kong S.A.R.');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (99, 'HU', 'Hungary');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (100, 'IS', 'Iceland');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (101, 'IN', 'India');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (102, 'ID', 'Indonesia');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (103, 'IR', 'Iran');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (104, 'IQ', 'Iraq');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (105, 'IE', 'Ireland');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (106, 'IL', 'Israel');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (107, 'IT', 'Italy');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (108, 'JM', 'Jamaica');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (109, 'JP', 'Japan');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (110, 'XJ', 'Jersey');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (111, 'JO', 'Jordan');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (112, 'KZ', 'Kazakhstan');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (113, 'KE', 'Kenya');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (114, 'KI', 'Kiribati');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (115, 'KP', 'Korea North');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (116, 'KR', 'Korea South');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (117, 'KW', 'Kuwait');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (118, 'KG', 'Kyrgyzstan');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (119, 'LA', 'Laos');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (120, 'LV', 'Latvia');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (121, 'LB', 'Lebanon');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (122, 'LS', 'Lesotho');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (123, 'LR', 'Liberia');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (124, 'LY', 'Libya');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (125, 'LI', 'Liechtenstein');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (126, 'LT', 'Lithuania');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (127, 'LU', 'Luxembourg');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (128, 'MO', 'Macau S.A.R.');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (129, 'MK', 'Macedonia');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (130, 'MG', 'Madagascar');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (131, 'MW', 'Malawi');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (132, 'MY', 'Malaysia');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (133, 'MV', 'Maldives');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (134, 'ML', 'Mali');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (135, 'MT', 'Malta');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (136, 'XM', 'Man (Isle of)');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (137, 'MH', 'Marshall Islands');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (138, 'MQ', 'Martinique');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (139, 'MR', 'Mauritania');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (140, 'MU', 'Mauritius');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (141, 'YT', 'Mayotte');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (142, 'MX', 'Mexico');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (143, 'FM', 'Micronesia');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (144, 'MD', 'Moldova');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (145, 'MC', 'Monaco');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (146, 'MN', 'Mongolia');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (147, 'MS', 'Montserrat');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (148, 'MA', 'Morocco');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (149, 'MZ', 'Mozambique');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (150, 'MM', 'Myanmar');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (151, 'NA', 'Namibia');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (152, 'NR', 'Nauru');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (153, 'NP', 'Nepal');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (154, 'AN', 'Netherlands Antilles');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (155, 'NL', 'Netherlands The');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (156, 'NC', 'New Caledonia');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (157, 'NZ', 'New Zealand');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (158, 'NI', 'Nicaragua');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (159, 'NE', 'Niger');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (160, 'NG', 'Nigeria');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (161, 'NU', 'Niue');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (162, 'NF', 'Norfolk Island');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (163, 'MP', 'Northern Mariana Islands');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (164, 'NO', 'Norway');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (165, 'OM', 'Oman');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (166, 'PK', 'Pakistan');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (167, 'PW', 'Palau');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (168, 'PS', 'Palestinian Territory Occupied');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (169, 'PA', 'Panama');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (170, 'PG', 'Papua new Guinea');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (171, 'PY', 'Paraguay');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (172, 'PE', 'Peru');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (173, 'PH', 'Philippines');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (174, 'PN', 'Pitcairn Island');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (175, 'PL', 'Poland');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (176, 'PT', 'Portugal');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (177, 'PR', 'Puerto Rico');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (178, 'QA', 'Qatar');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (179, 'RE', 'Reunion');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (180, 'RO', 'Romania');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (181, 'RU', 'Russia');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (182, 'RW', 'Rwanda');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (183, 'SH', 'Saint Helena');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (184, 'KN', 'Saint Kitts And Nevis');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (185, 'LC', 'Saint Lucia');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (186, 'PM', 'Saint Pierre and Miquelon');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (187, 'VC', 'Saint Vincent And The Grenadines');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (188, 'WS', 'Samoa');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (189, 'SM', 'San Marino');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (190, 'ST', 'Sao Tome and Principe');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (191, 'SA', 'Saudi Arabia');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (192, 'SN', 'Senegal');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (193, 'RS', 'Serbia');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (194, 'SC', 'Seychelles');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (195, 'SL', 'Sierra Leone');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (196, 'SG', 'Singapore');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (197, 'SK', 'Slovakia');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (198, 'SI', 'Slovenia');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (199, 'XG', 'Smaller Territories of the UK');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (200, 'SB', 'Solomon Islands');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (201, 'SO', 'Somalia');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (202, 'ZA', 'South Africa');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (203, 'GS', 'South Georgia');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (204, 'SS', 'South Sudan');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (205, 'ES', 'Spain');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (206, 'LK', 'Sri Lanka');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (207, 'SD', 'Sudan');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (208, 'SR', 'Suriname');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (209, 'SJ', 'Svalbard And Jan Mayen Islands');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (210, 'SZ', 'Swaziland');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (211, 'SE', 'Sweden');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (212, 'CH', 'Switzerland');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (213, 'SY', 'Syria');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (214, 'TW', 'Taiwan');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (215, 'TJ', 'Tajikistan');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (216, 'TZ', 'Tanzania');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (217, 'TH', 'Thailand');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (218, 'TG', 'Togo');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (219, 'TK', 'Tokelau');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (220, 'TO', 'Tonga');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (221, 'TT', 'Trinidad And Tobago');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (222, 'TN', 'Tunisia');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (223, 'TR', 'Turkey');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (224, 'TM', 'Turkmenistan');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (225, 'TC', 'Turks And Caicos Islands');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (226, 'TV', 'Tuvalu');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (227, 'UG', 'Uganda');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (228, 'UA', 'Ukraine');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (229, 'AE', 'United Arab Emirates');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (230, 'GB', 'United Kingdom');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (231, 'US', 'United States');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (232, 'UM', 'United States Minor Outlying Islands');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (233, 'UY', 'Uruguay');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (234, 'UZ', 'Uzbekistan');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (235, 'VU', 'Vanuatu');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (236, 'VA', 'Vatican City State (Holy See)');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (237, 'VE', 'Venezuela');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (238, 'VN', 'Vietnam');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (239, 'VG', 'Virgin Islands (British)');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (240, 'VI', 'Virgin Islands (US)');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (241, 'WF', 'Wallis And Futuna Islands');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (242, 'EH', 'Western Sahara');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (243, 'YE', 'Yemen');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (244, 'YU', 'Yugoslavia');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (245, 'ZM', 'Zambia');
INSERT INTO travelbook.countries (id, short_name, name) VALUES (246, 'ZW', 'Zimbabwe');
