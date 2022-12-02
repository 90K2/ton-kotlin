package org.ton.proxy.rldp.fec.raptorq

class RawParams(
    val kPadded: Int,
    val j: Int,
    val s: Int,
    val h: Int,
    val w: Int
) {
    companion object {
        @JvmStatic
        fun fromK(k: Int) = PARAMS_TABLE.find { it.kPadded >= k } ?: throw IllegalArgumentException("k is too big")
    }
}

private val PARAMS_TABLE = arrayOf(
    RawParams(10, 254, 7, 10, 17),
    RawParams(12, 630, 7, 10, 19),
    RawParams(18, 682, 11, 10, 29),
    RawParams(20, 293, 11, 10, 31),
    RawParams(26, 80, 11, 10, 37),
    RawParams(30, 566, 11, 10, 41),
    RawParams(32, 860, 11, 10, 43),
    RawParams(36, 267, 11, 10, 47),
    RawParams(42, 822, 11, 10, 53),
    RawParams(46, 506, 13, 10, 59),
    RawParams(48, 589, 13, 10, 61),
    RawParams(49, 87, 13, 10, 61),
    RawParams(55, 520, 13, 10, 67),
    RawParams(60, 159, 13, 10, 71),
    RawParams(62, 235, 13, 10, 73),
    RawParams(69, 157, 13, 10, 79),
    RawParams(75, 502, 17, 10, 89),
    RawParams(84, 334, 17, 10, 97),
    RawParams(88, 583, 17, 10, 101),
    RawParams(91, 66, 17, 10, 103),
    RawParams(95, 352, 17, 10, 107),
    RawParams(97, 365, 17, 10, 109),
    RawParams(101, 562, 17, 10, 113),
    RawParams(114, 5, 19, 10, 127),
    RawParams(119, 603, 19, 10, 131),
    RawParams(125, 721, 19, 10, 137),
    RawParams(127, 28, 19, 10, 139),
    RawParams(138, 660, 19, 10, 149),
    RawParams(140, 829, 19, 10, 151),
    RawParams(149, 900, 23, 10, 163),
    RawParams(153, 930, 23, 10, 167),
    RawParams(160, 814, 23, 10, 173),
    RawParams(166, 661, 23, 10, 179),
    RawParams(168, 693, 23, 10, 181),
    RawParams(179, 780, 23, 10, 191),
    RawParams(181, 605, 23, 10, 193),
    RawParams(185, 551, 23, 10, 197),
    RawParams(187, 777, 23, 10, 199),
    RawParams(200, 491, 23, 10, 211),
    RawParams(213, 396, 23, 10, 223),
    RawParams(217, 764, 29, 10, 233),
    RawParams(225, 843, 29, 10, 241),
    RawParams(236, 646, 29, 10, 251),
    RawParams(242, 557, 29, 10, 257),
    RawParams(248, 608, 29, 10, 263),
    RawParams(257, 265, 29, 10, 271),
    RawParams(263, 505, 29, 10, 277),
    RawParams(269, 722, 29, 10, 283),
    RawParams(280, 263, 29, 10, 293),
    RawParams(295, 999, 29, 10, 307),
    RawParams(301, 874, 29, 10, 313),
    RawParams(305, 160, 29, 10, 317),
    RawParams(324, 575, 31, 10, 337),
    RawParams(337, 210, 31, 10, 349),
    RawParams(341, 513, 31, 10, 353),
    RawParams(347, 503, 31, 10, 359),
    RawParams(355, 558, 31, 10, 367),
    RawParams(362, 932, 31, 10, 373),
    RawParams(368, 404, 31, 10, 379),
    RawParams(372, 520, 37, 10, 389),
    RawParams(380, 846, 37, 10, 397),
    RawParams(385, 485, 37, 10, 401),
    RawParams(393, 728, 37, 10, 409),
    RawParams(405, 554, 37, 10, 421),
    RawParams(418, 471, 37, 10, 433),
    RawParams(428, 641, 37, 10, 443),
    RawParams(434, 732, 37, 10, 449),
    RawParams(447, 193, 37, 10, 461),
    RawParams(453, 934, 37, 10, 467),
    RawParams(466, 864, 37, 10, 479),
    RawParams(478, 790, 37, 10, 491),
    RawParams(486, 912, 37, 10, 499),
    RawParams(491, 617, 37, 10, 503),
    RawParams(497, 587, 37, 10, 509),
    RawParams(511, 800, 37, 10, 523),
    RawParams(526, 923, 41, 10, 541),
    RawParams(532, 998, 41, 10, 547),
    RawParams(542, 92, 41, 10, 557),
    RawParams(549, 497, 41, 10, 563),
    RawParams(557, 559, 41, 10, 571),
    RawParams(563, 667, 41, 10, 577),
    RawParams(573, 912, 41, 10, 587),
    RawParams(580, 262, 41, 10, 593),
    RawParams(588, 152, 41, 10, 601),
    RawParams(594, 526, 41, 10, 607),
    RawParams(600, 268, 41, 10, 613),
    RawParams(606, 212, 41, 10, 619),
    RawParams(619, 45, 41, 10, 631),
    RawParams(633, 898, 43, 10, 647),
    RawParams(640, 527, 43, 10, 653),
    RawParams(648, 558, 43, 10, 661),
    RawParams(666, 460, 47, 10, 683),
    RawParams(675, 5, 47, 10, 691),
    RawParams(685, 895, 47, 10, 701),
    RawParams(693, 996, 47, 10, 709),
    RawParams(703, 282, 47, 10, 719),
    RawParams(718, 513, 47, 10, 733),
    RawParams(728, 865, 47, 10, 743),
    RawParams(736, 870, 47, 10, 751),
    RawParams(747, 239, 47, 10, 761),
    RawParams(759, 452, 47, 10, 773),
    RawParams(778, 862, 53, 10, 797),
    RawParams(792, 852, 53, 10, 811),
    RawParams(802, 643, 53, 10, 821),
    RawParams(811, 543, 53, 10, 829),
    RawParams(821, 447, 53, 10, 839),
    RawParams(835, 321, 53, 10, 853),
    RawParams(845, 287, 53, 10, 863),
    RawParams(860, 12, 53, 10, 877),
    RawParams(870, 251, 53, 10, 887),
    RawParams(891, 30, 53, 10, 907),
    RawParams(903, 621, 53, 10, 919),
    RawParams(913, 555, 53, 10, 929),
    RawParams(926, 127, 53, 10, 941),
    RawParams(938, 400, 53, 10, 953),
    RawParams(950, 91, 59, 10, 971),
    RawParams(963, 916, 59, 10, 983),
    RawParams(977, 935, 59, 10, 997),
    RawParams(989, 691, 59, 10, 1009),
    RawParams(1002, 299, 59, 10, 1021),
    RawParams(1020, 282, 59, 10, 1039),
    RawParams(1032, 824, 59, 10, 1051),
    RawParams(1050, 536, 59, 11, 1069),
    RawParams(1074, 596, 59, 11, 1093),
    RawParams(1085, 28, 59, 11, 1103),
    RawParams(1099, 947, 59, 11, 1117),
    RawParams(1111, 162, 59, 11, 1129),
    RawParams(1136, 536, 59, 11, 1153),
    RawParams(1152, 1000, 61, 11, 1171),
    RawParams(1169, 251, 61, 11, 1187),
    RawParams(1183, 673, 61, 11, 1201),
    RawParams(1205, 559, 61, 11, 1223),
    RawParams(1220, 923, 61, 11, 1237),
    RawParams(1236, 81, 67, 11, 1259),
    RawParams(1255, 478, 67, 11, 1277),
    RawParams(1269, 198, 67, 11, 1291),
    RawParams(1285, 137, 67, 11, 1307),
    RawParams(1306, 75, 67, 11, 1327),
    RawParams(1347, 29, 67, 11, 1367),
    RawParams(1361, 231, 67, 11, 1381),
    RawParams(1389, 532, 67, 11, 1409),
    RawParams(1404, 58, 67, 11, 1423),
    RawParams(1420, 60, 67, 11, 1439),
    RawParams(1436, 964, 71, 11, 1459),
    RawParams(1461, 624, 71, 11, 1483),
    RawParams(1477, 502, 71, 11, 1499),
    RawParams(1502, 636, 71, 11, 1523),
    RawParams(1522, 986, 71, 11, 1543),
    RawParams(1539, 950, 71, 11, 1559),
    RawParams(1561, 735, 73, 11, 1583),
    RawParams(1579, 866, 73, 11, 1601),
    RawParams(1600, 203, 73, 11, 1621),
    RawParams(1616, 83, 73, 11, 1637),
    RawParams(1649, 14, 73, 11, 1669),
    RawParams(1673, 522, 79, 11, 1699),
    RawParams(1698, 226, 79, 11, 1723),
    RawParams(1716, 282, 79, 11, 1741),
    RawParams(1734, 88, 79, 11, 1759),
    RawParams(1759, 636, 79, 11, 1783),
    RawParams(1777, 860, 79, 11, 1801),
    RawParams(1800, 324, 79, 11, 1823),
    RawParams(1824, 424, 79, 11, 1847),
    RawParams(1844, 999, 79, 11, 1867),
    RawParams(1863, 682, 83, 11, 1889),
    RawParams(1887, 814, 83, 11, 1913),
    RawParams(1906, 979, 83, 11, 1931),
    RawParams(1926, 538, 83, 11, 1951),
    RawParams(1954, 278, 83, 11, 1979),
    RawParams(1979, 580, 83, 11, 2003),
    RawParams(2005, 773, 83, 11, 2029),
    RawParams(2040, 911, 89, 11, 2069),
    RawParams(2070, 506, 89, 11, 2099),
    RawParams(2103, 628, 89, 11, 2131),
    RawParams(2125, 282, 89, 11, 2153),
    RawParams(2152, 309, 89, 11, 2179),
    RawParams(2195, 858, 89, 11, 2221),
    RawParams(2217, 442, 89, 11, 2243),
    RawParams(2247, 654, 89, 11, 2273),
    RawParams(2278, 82, 97, 11, 2311),
    RawParams(2315, 428, 97, 11, 2347),
    RawParams(2339, 442, 97, 11, 2371),
    RawParams(2367, 283, 97, 11, 2399),
    RawParams(2392, 538, 97, 11, 2423),
    RawParams(2416, 189, 97, 11, 2447),
    RawParams(2447, 438, 97, 11, 2477),
    RawParams(2473, 912, 97, 11, 2503),
    RawParams(2502, 1, 97, 11, 2531),
    RawParams(2528, 167, 97, 11, 2557),
    RawParams(2565, 272, 97, 11, 2593),
    RawParams(2601, 209, 101, 11, 2633),
    RawParams(2640, 927, 101, 11, 2671),
    RawParams(2668, 386, 101, 11, 2699),
    RawParams(2701, 653, 101, 11, 2731),
    RawParams(2737, 669, 101, 11, 2767),
    RawParams(2772, 431, 101, 11, 2801),
    RawParams(2802, 793, 103, 11, 2833),
    RawParams(2831, 588, 103, 11, 2861),
    RawParams(2875, 777, 107, 11, 2909),
    RawParams(2906, 939, 107, 11, 2939),
    RawParams(2938, 864, 107, 11, 2971),
    RawParams(2979, 627, 107, 11, 3011),
    RawParams(3015, 265, 109, 11, 3049),
    RawParams(3056, 976, 109, 11, 3089),
    RawParams(3101, 988, 113, 11, 3137),
    RawParams(3151, 507, 113, 11, 3187),
    RawParams(3186, 640, 113, 11, 3221),
    RawParams(3224, 15, 113, 11, 3259),
    RawParams(3265, 667, 113, 11, 3299),
    RawParams(3299, 24, 127, 11, 3347),
    RawParams(3344, 877, 127, 11, 3391),
    RawParams(3387, 240, 127, 11, 3433),
    RawParams(3423, 720, 127, 11, 3469),
    RawParams(3466, 93, 127, 11, 3511),
    RawParams(3502, 919, 127, 11, 3547),
    RawParams(3539, 635, 127, 11, 3583),
    RawParams(3579, 174, 127, 11, 3623),
    RawParams(3616, 647, 127, 11, 3659),
    RawParams(3658, 820, 127, 11, 3701),
    RawParams(3697, 56, 127, 11, 3739),
    RawParams(3751, 485, 127, 11, 3793),
    RawParams(3792, 210, 127, 11, 3833),
    RawParams(3840, 124, 127, 11, 3881),
    RawParams(3883, 546, 127, 11, 3923),
    RawParams(3924, 954, 131, 11, 3967),
    RawParams(3970, 262, 131, 11, 4013),
    RawParams(4015, 927, 131, 11, 4057),
    RawParams(4069, 957, 131, 11, 4111),
    RawParams(4112, 726, 137, 11, 4159),
    RawParams(4165, 583, 137, 11, 4211),
    RawParams(4207, 782, 137, 11, 4253),
    RawParams(4252, 37, 137, 11, 4297),
    RawParams(4318, 758, 137, 11, 4363),
    RawParams(4365, 777, 137, 11, 4409),
    RawParams(4418, 104, 139, 11, 4463),
    RawParams(4468, 476, 139, 11, 4513),
    RawParams(4513, 113, 149, 11, 4567),
    RawParams(4567, 313, 149, 11, 4621),
    RawParams(4626, 102, 149, 11, 4679),
    RawParams(4681, 501, 149, 11, 4733),
    RawParams(4731, 332, 149, 11, 4783),
    RawParams(4780, 786, 149, 11, 4831),
    RawParams(4838, 99, 149, 11, 4889),
    RawParams(4901, 658, 149, 11, 4951),
    RawParams(4954, 794, 149, 11, 5003),
    RawParams(5008, 37, 151, 11, 5059),
    RawParams(5063, 471, 151, 11, 5113),
    RawParams(5116, 94, 157, 11, 5171),
    RawParams(5172, 873, 157, 11, 5227),
    RawParams(5225, 918, 157, 11, 5279),
    RawParams(5279, 945, 157, 11, 5333),
    RawParams(5334, 211, 157, 11, 5387),
    RawParams(5391, 341, 157, 11, 5443),
    RawParams(5449, 11, 163, 11, 5507),
    RawParams(5506, 578, 163, 11, 5563),
    RawParams(5566, 494, 163, 11, 5623),
    RawParams(5637, 694, 163, 11, 5693),
    RawParams(5694, 252, 163, 11, 5749),
    RawParams(5763, 451, 167, 11, 5821),
    RawParams(5823, 83, 167, 11, 5881),
    RawParams(5896, 689, 167, 11, 5953),
    RawParams(5975, 488, 173, 11, 6037),
    RawParams(6039, 214, 173, 11, 6101),
    RawParams(6102, 17, 173, 11, 6163),
    RawParams(6169, 469, 173, 11, 6229),
    RawParams(6233, 263, 179, 11, 6299),
    RawParams(6296, 309, 179, 11, 6361),
    RawParams(6363, 984, 179, 11, 6427),
    RawParams(6427, 123, 179, 11, 6491),
    RawParams(6518, 360, 179, 11, 6581),
    RawParams(6589, 863, 181, 11, 6653),
    RawParams(6655, 122, 181, 11, 6719),
    RawParams(6730, 522, 191, 11, 6803),
    RawParams(6799, 539, 191, 11, 6871),
    RawParams(6878, 181, 191, 11, 6949),
    RawParams(6956, 64, 191, 11, 7027),
    RawParams(7033, 387, 191, 11, 7103),
    RawParams(7108, 967, 191, 11, 7177),
    RawParams(7185, 843, 191, 11, 7253),
    RawParams(7281, 999, 193, 11, 7351),
    RawParams(7360, 76, 197, 11, 7433),
    RawParams(7445, 142, 197, 11, 7517),
    RawParams(7520, 599, 197, 11, 7591),
    RawParams(7596, 576, 199, 11, 7669),
    RawParams(7675, 176, 211, 11, 7759),
    RawParams(7770, 392, 211, 11, 7853),
    RawParams(7855, 332, 211, 11, 7937),
    RawParams(7935, 291, 211, 11, 8017),
    RawParams(8030, 913, 211, 11, 8111),
    RawParams(8111, 608, 211, 11, 8191),
    RawParams(8194, 212, 211, 11, 8273),
    RawParams(8290, 696, 211, 11, 8369),
    RawParams(8377, 931, 223, 11, 8467),
    RawParams(8474, 326, 223, 11, 8563),
    RawParams(8559, 228, 223, 11, 8647),
    RawParams(8654, 706, 223, 11, 8741),
    RawParams(8744, 144, 223, 11, 8831),
    RawParams(8837, 83, 223, 11, 8923),
    RawParams(8928, 743, 223, 11, 9013),
    RawParams(9019, 187, 223, 11, 9103),
    RawParams(9111, 654, 227, 11, 9199),
    RawParams(9206, 359, 227, 11, 9293),
    RawParams(9303, 493, 229, 11, 9391),
    RawParams(9400, 369, 233, 11, 9491),
    RawParams(9497, 981, 233, 11, 9587),
    RawParams(9601, 276, 239, 11, 9697),
    RawParams(9708, 647, 239, 11, 9803),
    RawParams(9813, 389, 239, 11, 9907),
    RawParams(9916, 80, 239, 11, 10009),
    RawParams(10017, 396, 241, 11, 10111),
    RawParams(10120, 580, 251, 11, 10223),
    RawParams(10241, 873, 251, 11, 10343),
    RawParams(10351, 15, 251, 11, 10453),
    RawParams(10458, 976, 251, 11, 10559),
    RawParams(10567, 584, 251, 11, 10667),
    RawParams(10676, 267, 257, 11, 10781),
    RawParams(10787, 876, 257, 11, 10891),
    RawParams(10899, 642, 257, 12, 11003),
    RawParams(11015, 794, 257, 12, 11119),
    RawParams(11130, 78, 263, 12, 11239),
    RawParams(11245, 736, 263, 12, 11353),
    RawParams(11358, 882, 269, 12, 11471),
    RawParams(11475, 251, 269, 12, 11587),
    RawParams(11590, 434, 269, 12, 11701),
    RawParams(11711, 204, 269, 12, 11821),
    RawParams(11829, 256, 271, 12, 11941),
    RawParams(11956, 106, 277, 12, 12073),
    RawParams(12087, 375, 277, 12, 12203),
    RawParams(12208, 148, 277, 12, 12323),
    RawParams(12333, 496, 281, 12, 12451),
    RawParams(12460, 88, 281, 12, 12577),
    RawParams(12593, 826, 293, 12, 12721),
    RawParams(12726, 71, 293, 12, 12853),
    RawParams(12857, 925, 293, 12, 12983),
    RawParams(13002, 760, 293, 12, 13127),
    RawParams(13143, 130, 293, 12, 13267),
    RawParams(13284, 641, 307, 12, 13421),
    RawParams(13417, 400, 307, 12, 13553),
    RawParams(13558, 480, 307, 12, 13693),
    RawParams(13695, 76, 307, 12, 13829),
    RawParams(13833, 665, 307, 12, 13967),
    RawParams(13974, 910, 307, 12, 14107),
    RawParams(14115, 467, 311, 12, 14251),
    RawParams(14272, 964, 311, 12, 14407),
    RawParams(14415, 625, 313, 12, 14551),
    RawParams(14560, 362, 317, 12, 14699),
    RawParams(14713, 759, 317, 12, 14851),
    RawParams(14862, 728, 331, 12, 15013),
    RawParams(15011, 343, 331, 12, 15161),
    RawParams(15170, 113, 331, 12, 15319),
    RawParams(15325, 137, 331, 12, 15473),
    RawParams(15496, 308, 331, 12, 15643),
    RawParams(15651, 800, 337, 12, 15803),
    RawParams(15808, 177, 337, 12, 15959),
    RawParams(15977, 961, 337, 12, 16127),
    RawParams(16161, 958, 347, 12, 16319),
    RawParams(16336, 72, 347, 12, 16493),
    RawParams(16505, 732, 347, 12, 16661),
    RawParams(16674, 145, 349, 12, 16831),
    RawParams(16851, 577, 353, 12, 17011),
    RawParams(17024, 305, 353, 12, 17183),
    RawParams(17195, 50, 359, 12, 17359),
    RawParams(17376, 351, 359, 12, 17539),
    RawParams(17559, 175, 367, 12, 17729),
    RawParams(17742, 727, 367, 12, 17911),
    RawParams(17929, 902, 367, 12, 18097),
    RawParams(18116, 409, 373, 12, 18289),
    RawParams(18309, 776, 373, 12, 18481),
    RawParams(18503, 586, 379, 12, 18679),
    RawParams(18694, 451, 379, 12, 18869),
    RawParams(18909, 287, 383, 12, 19087),
    RawParams(19126, 246, 389, 12, 19309),
    RawParams(19325, 222, 389, 12, 19507),
    RawParams(19539, 563, 397, 12, 19727),
    RawParams(19740, 839, 397, 12, 19927),
    RawParams(19939, 897, 401, 12, 20129),
    RawParams(20152, 409, 401, 12, 20341),
    RawParams(20355, 618, 409, 12, 20551),
    RawParams(20564, 439, 409, 12, 20759),
    RawParams(20778, 95, 419, 13, 20983),
    RawParams(20988, 448, 419, 13, 21191),
    RawParams(21199, 133, 419, 13, 21401),
    RawParams(21412, 938, 419, 13, 21613),
    RawParams(21629, 423, 431, 13, 21841),
    RawParams(21852, 90, 431, 13, 22063),
    RawParams(22073, 640, 431, 13, 22283),
    RawParams(22301, 922, 433, 13, 22511),
    RawParams(22536, 250, 439, 13, 22751),
    RawParams(22779, 367, 439, 13, 22993),
    RawParams(23010, 447, 443, 13, 23227),
    RawParams(23252, 559, 449, 13, 23473),
    RawParams(23491, 121, 457, 13, 23719),
    RawParams(23730, 623, 457, 13, 23957),
    RawParams(23971, 450, 457, 13, 24197),
    RawParams(24215, 253, 461, 13, 24443),
    RawParams(24476, 106, 467, 13, 24709),
    RawParams(24721, 863, 467, 13, 24953),
    RawParams(24976, 148, 479, 13, 25219),
    RawParams(25230, 427, 479, 13, 25471),
    RawParams(25493, 138, 479, 13, 25733),
    RawParams(25756, 794, 487, 13, 26003),
    RawParams(26022, 247, 487, 13, 26267),
    RawParams(26291, 562, 491, 13, 26539),
    RawParams(26566, 53, 499, 13, 26821),
    RawParams(26838, 135, 499, 13, 27091),
    RawParams(27111, 21, 503, 13, 27367),
    RawParams(27392, 201, 509, 13, 27653),
    RawParams(27682, 169, 521, 13, 27953),
    RawParams(27959, 70, 521, 13, 28229),
    RawParams(28248, 386, 521, 13, 28517),
    RawParams(28548, 226, 523, 13, 28817),
    RawParams(28845, 3, 541, 13, 29131),
    RawParams(29138, 769, 541, 13, 29423),
    RawParams(29434, 590, 541, 13, 29717),
    RawParams(29731, 672, 541, 13, 30013),
    RawParams(30037, 713, 547, 13, 30323),
    RawParams(30346, 967, 547, 13, 30631),
    RawParams(30654, 368, 557, 14, 30949),
    RawParams(30974, 348, 557, 14, 31267),
    RawParams(31285, 119, 563, 14, 31583),
    RawParams(31605, 503, 569, 14, 31907),
    RawParams(31948, 181, 571, 14, 32251),
    RawParams(32272, 394, 577, 14, 32579),
    RawParams(32601, 189, 587, 14, 32917),
    RawParams(32932, 210, 587, 14, 33247),
    RawParams(33282, 62, 593, 14, 33601),
    RawParams(33623, 273, 593, 14, 33941),
    RawParams(33961, 554, 599, 14, 34283),
    RawParams(34302, 936, 607, 14, 34631),
    RawParams(34654, 483, 607, 14, 34981),
    RawParams(35031, 397, 613, 14, 35363),
    RawParams(35395, 241, 619, 14, 35731),
    RawParams(35750, 500, 631, 14, 36097),
    RawParams(36112, 12, 631, 14, 36457),
    RawParams(36479, 958, 641, 14, 36833),
    RawParams(36849, 524, 641, 14, 37201),
    RawParams(37227, 8, 643, 14, 37579),
    RawParams(37606, 100, 653, 14, 37967),
    RawParams(37992, 339, 653, 14, 38351),
    RawParams(38385, 804, 659, 14, 38749),
    RawParams(38787, 510, 673, 14, 39163),
    RawParams(39176, 18, 673, 14, 39551),
    RawParams(39576, 412, 677, 14, 39953),
    RawParams(39980, 394, 683, 14, 40361),
    RawParams(40398, 830, 691, 15, 40787),
    RawParams(40816, 535, 701, 15, 41213),
    RawParams(41226, 199, 701, 15, 41621),
    RawParams(41641, 27, 709, 15, 42043),
    RawParams(42067, 298, 709, 15, 42467),
    RawParams(42490, 368, 719, 15, 42899),
    RawParams(42916, 755, 727, 15, 43331),
    RawParams(43388, 379, 727, 15, 43801),
    RawParams(43840, 73, 733, 15, 44257),
    RawParams(44279, 387, 739, 15, 44701),
    RawParams(44729, 457, 751, 15, 45161),
    RawParams(45183, 761, 751, 15, 45613),
    RawParams(45638, 855, 757, 15, 46073),
    RawParams(46104, 370, 769, 15, 46549),
    RawParams(46574, 261, 769, 15, 47017),
    RawParams(47047, 299, 787, 15, 47507),
    RawParams(47523, 920, 787, 15, 47981),
    RawParams(48007, 269, 787, 15, 48463),
    RawParams(48489, 862, 797, 15, 48953),
    RawParams(48976, 349, 809, 15, 49451),
    RawParams(49470, 103, 809, 15, 49943),
    RawParams(49978, 115, 821, 15, 50461),
    RawParams(50511, 93, 821, 16, 50993),
    RawParams(51017, 982, 827, 16, 51503),
    RawParams(51530, 432, 839, 16, 52027),
    RawParams(52062, 340, 853, 16, 52571),
    RawParams(52586, 173, 853, 16, 53093),
    RawParams(53114, 421, 857, 16, 53623),
    RawParams(53650, 330, 863, 16, 54163),
    RawParams(54188, 624, 877, 16, 54713),
    RawParams(54735, 233, 877, 16, 55259),
    RawParams(55289, 362, 883, 16, 55817),
    RawParams(55843, 963, 907, 16, 56393),
    RawParams(56403, 471, 907, 16, 56951)
)