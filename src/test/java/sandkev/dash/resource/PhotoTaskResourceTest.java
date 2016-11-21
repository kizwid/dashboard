package sandkev.dash.resource;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by kevin on 20/11/2016.
 */
public class PhotoTaskResourceTest {

    @Test
    public void doIt() throws IOException {
        PhotoTaskResource photoTaskResource = new PhotoTaskResource(null);
        photoTaskResource.runTask("");
    }

    /*
    duplicates found using Hashing.murmur3_128
d207f58e9fcdf13f75691c888e542ef1=[/Users/kevin/Google Drive/photos/201009_MimisFirstDayInUniform/IMG_7498.JPG, /Users/kevin/Google Drive/photos/201007_SummerInCuhon/IMG_7502.JPG]
bb34c44ba3372e772269ff78e6656bcd=[/Users/kevin/Google Drive/photos/201501_KevinsCamera/IMG_0450.JPG, /Users/kevin/Google Drive/photos/201501_KevinsCamera/IMG_0449.JPG]
0f90754d585a9209effa499b1973a8ee=[/Users/kevin/Google Drive/photos/200704_Newborn/Image013_1.jpg, /Users/kevin/Google Drive/photos/200704_Newborn/Image014_1.jpg]
f81725e74a5dbf084849541e41e241d4=[/Users/kevin/Google Drive/photos/201009_MimisFirstDayInUniform/IMG_7522.JPG, /Users/kevin/Google Drive/photos/201007_SummerInCuhon/IMG_7522.JPG]
2fef2172ac2e454f2f2c3edb230c87e3=[/Users/kevin/Google Drive/photos/200605_bluebells/Originals/PICT2578.JPG, /Users/kevin/Google Drive/photos/200605_bluebells/PICT2578.JPG]
ae17744a3da3fdb26c00399142c705d8=[/Users/kevin/Google Drive/photos/200504/PICT1443.JPG, /Users/kevin/Google Drive/photos/200503_Lanyon/PICT1443.JPG]
adaae63c584f28359b482dfea30dfb0d=[/Users/kevin/Google Drive/photos/200712_Chistmas/Originals/IMG_0116.1.jpg, /Users/kevin/Google Drive/photos/200712_Chistmas/Originals/IMG_0116.JPG]
db9e5d7a2b6f5ca1c33b097133df9f90=[/Users/kevin/Google Drive/photos/200604_Climbing/PICT2521.JPG, /Users/kevin/Google Drive/photos/200604_PalmSunday/PICT2518.JPG]
f8d8814b3434b36e31eb8789163f3d56=[/Users/kevin/Google Drive/photos/201007_SummerInCuhon/IMG_7491.JPG, /Users/kevin/Google Drive/photos/201009_Lily'sBirthdayParty/IMG_7491.JPG]
831bb5708161aac2513b8322cffcbbdf=[/Users/kevin/Google Drive/photos/mixed-keep/DSC01324.JPG, /Users/kevin/Google Drive/photos/200511_FuertaVentura/PICT2124.JPG]
4cb52d4c1707f656ebdf03bd6fc66d8f=[/Users/kevin/Google Drive/photos/200511_FuertaVentura/DSC01319.JPG, /Users/kevin/Google Drive/photos/mixed-keep/DSC01319.JPG]
22b2a4cfa633fe48947cc2d77fee1917=[/Users/kevin/Google Drive/photos/mixed-keep/DSC01313.JPG, /Users/kevin/Google Drive/photos/200511_FuertaVentura/DSC01313.JPG]
dd190d3963a8646930468fd3b1cc7a37=[/Users/kevin/Google Drive/photos/201508_charlottesPhone-x/IMG_3925.JPG, /Users/kevin/Google Drive/photos/201508_charlottesPhone-x/IMG_3924.JPG]
e177d8594ba9c1b4bdf744c27e84c30a=[/Users/kevin/Google Drive/photos/200901_FromKevsMobile/Image065.jpg, /Users/kevin/Google Drive/photos/201003_KevinsMobile/Image065.jpg]
eebf19ccc6f630643691be62d459a935=[/Users/kevin/Google Drive/photos/200704_Newborn/Image015_1.jpg, /Users/kevin/Google Drive/photos/200704_Newborn/Image015.jpg]
cf49ec53208418ed03a8ffc63e5a211f=[/Users/kevin/Google Drive/photos/201009_GeorgeChristening/IMG_7403.JPG, /Users/kevin/Google Drive/photos/201007_SummerInCuhon/IMG_7349.JPG]
da9d0dc7cbe04dc58e31675663f5c4b5=[/Users/kevin/Google Drive/photos/mixed-keep/DSC01326.JPG, /Users/kevin/Google Drive/photos/200511_FuertaVentura/DSC01321.JPG]
70334369a288d3127b6b062ce9927a58=[/Users/kevin/Google Drive/photos/201009_Nana'sCamera/P7280111.JPG, /Users/kevin/Google Drive/photos/201007_SummerInCuhon/P8040134.JPG]
c82a74fa46a5962e1c161c39777c4495=[/Users/kevin/Google Drive/photos/200901_FromKevsMobile/Image053.jpg, /Users/kevin/Google Drive/photos/201003_KevinsMobile/Image053.jpg]
ce94d0ecf8aa55ec1d2490ff36a9decc=[/Users/kevin/Google Drive/photos/201501_KevinsCamera/IMG_0407.JPG, /Users/kevin/Google Drive/photos/201501_KevinsCamera/IMG_0407 (1).JPG]
4e6e473bf617786d81f3b74039d465d6=[/Users/kevin/Google Drive/photos/201508_charlottesPhone-x/IMG_4024 (1).JPG, /Users/kevin/Google Drive/photos/201508_charlottesPhone-x/IMG_4024.JPG]
8390384390c5479cb3cbb2bf364355f5=[/Users/kevin/Google Drive/photos/200503_Lanyon/PICT1448.JPG, /Users/kevin/Google Drive/photos/200504/PICT1446.JPG]
00d4224eecc5ff26bcc8af2a687486da=[/Users/kevin/Google Drive/photos/201007_SummerInCuhon/P8040141.JPG, /Users/kevin/Google Drive/photos/201009_Nana'sCamera/P8040141.JPG]
cc1a161472dc74908476dff271bc05b9=[/Users/kevin/Google Drive/photos/mixed-keep/DSC01306.JPG, /Users/kevin/Google Drive/photos/200511_FuertaVentura/DSC01306.JPG]
9f4916ed10292f6bf1fd8024891598a3=[/Users/kevin/Google Drive/photos/200511_Bedtime/PICT2250.JPG, /Users/kevin/Google Drive/photos/mixed-keep/DSC01290.JPG]
d6e488db47706f70c510f07729108e86=[/Users/kevin/Google Drive/photos/200503_Lanyon/PICT1445.JPG, /Users/kevin/Google Drive/photos/200504/PICT1448.JPG]
9a4e2eedbbce22519d2f28d90d41f3ec=[/Users/kevin/Google Drive/photos/200704_Newborn/Image010.jpg, /Users/kevin/Google Drive/photos/200704_Newborn/Image009.jpg]
c49bfc0142d31ecd132796bec55a928b=[/Users/kevin/Google Drive/photos/200504/PICT1445.JPG, /Users/kevin/Google Drive/photos/200503_Lanyon/PICT1444.JPG]
e353236fcf83c4c9d16472d586c94e22=[/Users/kevin/Google Drive/photos/200503/IMG_1274.JPG, /Users/kevin/Google Drive/photos/200503/march05 393.jpg]
42a755f741dc579c35ddc1e776dff9d9=[/Users/kevin/Google Drive/photos/200704_Newborn/Image000.jpg, /Users/kevin/Google Drive/photos/200704_NanaMeetsAmelia/IMG_3617.JPG]
5dfe600a2f010ef3edd9dd8668ea2c37=[/Users/kevin/Google Drive/photos/200604_PalmSunday/PICT2466.JPG, /Users/kevin/Google Drive/photos/200604_Climbing/PICT2528.JPG]
d1af563b24f9cfc9cf0d6e1afb01c0a1=[/Users/kevin/Google Drive/photos/201003_KevinsMobile/Image0023.jpg, /Users/kevin/Google Drive/photos/200901_FromKevsMobile/Image001.jpg]
642e93e0042b7e1780dcfc7c34e09074=[/Users/kevin/Google Drive/photos/201204_GeorgeByEmma/IMG_0146.JPG, /Users/kevin/Google Drive/photos/201204_GeorgeByEmma/photo (10b).JPG]
2caadce43ce49747a591f47c0f5c5e3f=[/Users/kevin/Google Drive/photos/200503_Lanyon/PICT1450.JPG, /Users/kevin/Google Drive/photos/200504/PICT1447.JPG]
ead1864a564d8613ba7de0b38ce6576d=[/Users/kevin/Google Drive/photos/201007_SummerInCuhon/Image0013-1.JPG, /Users/kevin/Google Drive/photos/201007_SummerInCuhon/Image0013.jpg, /Users/kevin/Google Drive/photos/201003_CharliesMobile/Image0015.jpg]
55456be9bf5815be04200f0c285133a2=[/Users/kevin/Google Drive/photos/200504/PICT1438.JPG, /Users/kevin/Google Drive/photos/200503_Lanyon/PICT1441.JPG]
6ce83c504bfa5f1f1de60cf88e46ba3c=[/Users/kevin/Google Drive/photos/201501_KevinsCamera/IMG_0062.JPG, /Users/kevin/Google Drive/photos/201501_KevinsCamera/IMG_0062 (1).JPG]
23e2e41e8908ce71b79bb036294c76c4=[/Users/kevin/Google Drive/photos/200504/PICT1440.JPG, /Users/kevin/Google Drive/photos/200503_Lanyon/PICT1442.JPG]
3cfdc649ea48816c7633a53114335ea9=[/Users/kevin/Google Drive/photos/200503_Lanyon/PICT1446.JPG, /Users/kevin/Google Drive/photos/200504/PICT1444.JPG]
c82439036f30ebaf867d1decc512dd54=[/Users/kevin/Google Drive/photos/200504/PICT1474.JPG, /Users/kevin/Google Drive/photos/200503_Lanyon/PICT1447.JPG]
12689 ms

dups found using sha1
f27a8cf3f36cb71aef1996714617004a8564355d=[/Users/kevin/Google Drive/photos/201007_SummerInCuhon/IMG_7498.JPG, /Users/kevin/Google Drive/photos/201009_MimisFirstDayInUniform/IMG_7498.JPG]
2a017767304f2f2642888ba3f00310fc9ac6f508=[/Users/kevin/Google Drive/photos/200604_Climbing/PICT2528.JPG, /Users/kevin/Google Drive/photos/200604_PalmSunday/PICT2534.JPG]
1be432abfc902b4db1d3ba4e89f9bf3aad0e76de=[/Users/kevin/Google Drive/photos/200503_Lanyon/PICT1449.JPG, /Users/kevin/Google Drive/photos/200504/PICT1448.JPG]
b9864f1ccfe2c51100e540c595d73d279f912a74=[/Users/kevin/Google Drive/photos/200604_PalmSunday/PICT2464.JPG, /Users/kevin/Google Drive/photos/200604_Climbing/PICT2466.JPG]
6d915d9702dc7c22eb4d2832b09419020555d061=[/Users/kevin/Google Drive/photos/200704_Newborn/Image015_1.jpg, /Users/kevin/Google Drive/photos/200704_Newborn/Image014.jpg]
84b01ec596f5e4b36033760db2c5d6879801f950=[/Users/kevin/Google Drive/photos/201007_SummerInCuhon/P8040141.JPG, /Users/kevin/Google Drive/photos/201009_Nana'sCamera/P8040141.JPG]
487b5b141900e0af8ff791465d45457925583f95=[/Users/kevin/Google Drive/photos/201501_KevinsCamera/IMG_0449.JPG, /Users/kevin/Google Drive/photos/201501_KevinsCamera/IMG_0449 (1).JPG]
534f1fc0284dfa7d40136dbfe9450875aebe9a24=[/Users/kevin/Google Drive/photos/200503_Lanyon/PICT1447.JPG, /Users/kevin/Google Drive/photos/200504/PICT1446.JPG]
a0a9f568bb4574cef736d3e94f65f93651d3a2d8=[/Users/kevin/Google Drive/photos/201508_charlottesPhone-x/IMG_4024 (1).JPG, /Users/kevin/Google Drive/photos/201508_charlottesPhone-x/IMG_4024.JPG]
4148b9560045202318a09c611d9afb9b9eedbe27=[/Users/kevin/Google Drive/photos/201003_CharliesMobile/Image0012.jpg, /Users/kevin/Google Drive/photos/201007_SummerInCuhon/Image0013-1.JPG, /Users/kevin/Google Drive/photos/201004_Mimi3rdBirthday/IMG_6876.JPG]
5cc13c7e1aab0efe8ec2b9f74cfe84bbdd5cca15=[/Users/kevin/Google Drive/photos/201009_MimisFirstDayInUniform/IMG_7522.JPG, /Users/kevin/Google Drive/photos/201007_SummerInCuhon/IMG_7522.JPG]
916dda45aa91a6c3f921d752953b315a4c7c054a=[/Users/kevin/Google Drive/photos/201007_SummerInCuhon/IMG_7516.JPG, /Users/kevin/Google Drive/photos/201009_Nana'sCamera/P7280111.JPG]
6c15977f0e1c22c05c9ba60c30a9de1a42024b0e=[/Users/kevin/Google Drive/photos/200503/IMG_1274.JPG, /Users/kevin/Google Drive/photos/200503/march05 379.jpg]
22d3ef9b63bfe4f404598760884fe00cf002a572=[/Users/kevin/Google Drive/photos/200503_Lanyon/PICT1446.JPG, /Users/kevin/Google Drive/photos/200504/PICT1445.JPG]
cab5c78e73d9ec33b58f47ae4167a366253366bc=[/Users/kevin/Google Drive/photos/201501_KevinsCamera/IMG_0062.JPG, /Users/kevin/Google Drive/photos/201501_KevinsCamera/IMG_0062 (1).JPG]
d6f038f20e0aae571c9e9fccc8315be25addd753=[/Users/kevin/Google Drive/photos/201003_KevinsMobile/Image065.jpg, /Users/kevin/Google Drive/photos/200901_FromKevsMobile/Image055.jpg]
90926df94bc0f70369da94f874f7ac99a1ef61c0=[/Users/kevin/Google Drive/photos/201501_KevinsCamera/IMG_0407.JPG, /Users/kevin/Google Drive/photos/201501_KevinsCamera/IMG_0407 (1).JPG]
dc6b72fefd660047fd1f6d125ddc6f79da775ff7=[/Users/kevin/Google Drive/photos/201508_charlottesPhone-x/IMG_3913.JPG, /Users/kevin/Google Drive/photos/201508_charlottesPhone-x/IMG_3924.JPG]
fb378e71045ba9d4fbef418cd6a9fa8600e707b1=[/Users/kevin/Google Drive/photos/200704_Newborn/Image009.jpg, /Users/kevin/Google Drive/photos/200704_Newborn/Image009_1.jpg]
336aed24e9999420aaca4070ea705d01864b99c6=[/Users/kevin/Google Drive/photos/200704_NanaMeetsAmelia/IMG_3618.JPG, /Users/kevin/Google Drive/photos/200704_NanaMeetsAmelia/IMG_3617.JPG]
9548382da333015ac004b1f469e773ef5ea9da53=[/Users/kevin/Google Drive/photos/200504/PICT1449.JPG, /Users/kevin/Google Drive/photos/200503_Lanyon/PICT1441.JPG]
1bf633582cf464b120beba4402964a41971b4c2d=[/Users/kevin/Google Drive/photos/mixed-keep/DSC01306.JPG, /Users/kevin/Google Drive/photos/200511_FuertaVentura/DSC01290.JPG]
d1a2e880d96f3bb30274bce17b0f1058c8309fab=[/Users/kevin/Google Drive/photos/200901_FromKevsMobile/Image065.jpg, /Users/kevin/Google Drive/photos/201003_KevinsMobile/Image036.jpg]
09610dc496758d991da39fce74a1f56dd108fd58=[/Users/kevin/Google Drive/photos/200503_Lanyon/PICT1448.JPG, /Users/kevin/Google Drive/photos/200504/PICT1447.JPG]
a1cf4d5f4404dbb27e09748fb48cae16bd83aa17=[/Users/kevin/Google Drive/photos/201204_GeorgeByEmma/IMG_0144.JPG, /Users/kevin/Google Drive/photos/201204_GeorgeByEmma/photo (10b).JPG]
85032f9fad5ec10768d36e2656b400224c919609=[/Users/kevin/Google Drive/photos/200503_Lanyon/PICT1437.JPG, /Users/kevin/Google Drive/photos/200504/PICT1440.JPG]
0c6e850afb8d6901d46fbf3b7b256fe8b523fa7e=[/Users/kevin/Google Drive/photos/201009_GeorgeChristening/IMG_7403.JPG, /Users/kevin/Google Drive/photos/201007_SummerInCuhon/IMG_7412.JPG]
151fb2bd220ad9eabe508eead068f4b2fa2287a8=[/Users/kevin/Google Drive/photos/200504/PICT1444.JPG, /Users/kevin/Google Drive/photos/200503_Lanyon/PICT1445.JPG]
09b6dd015c5d8912d93adb7ce82edbfcd81ee2a8=[/Users/kevin/Google Drive/photos/201007_SummerInCuhon/IMG_7491.JPG, /Users/kevin/Google Drive/photos/201009_Lily'sBirthdayParty/IMG_7491.JPG]
dcf09b434b91f239d20d3beb02fa2d73d0aa45d7=[/Users/kevin/Google Drive/photos/mixed-keep/DSC01326.JPG, /Users/kevin/Google Drive/photos/200511_FuertaVentura/DSC01324.JPG]
5c1133e7232a0491e588fcdb2b7f84df5e5ffa3c=[/Users/kevin/Google Drive/photos/mixed-keep/DSC01324.JPG, /Users/kevin/Google Drive/photos/200511_FuertaVentura/PICT2124.JPG]
5424b70fcf7f8a58db1ceab3cea34f598418c3a0=[/Users/kevin/Google Drive/photos/200504/PICT1443.JPG, /Users/kevin/Google Drive/photos/200503_Lanyon/PICT1444.JPG]
2b2f7849c64bf365945e71e8c4d2f83e5cd0d25a=[/Users/kevin/Google Drive/photos/200511_FuertaVentura/DSC01319.JPG, /Users/kevin/Google Drive/photos/mixed-keep/DSC01319.JPG]
4c6ff4eb1b78bcba790c98139768895d83e8b087=[/Users/kevin/Google Drive/photos/200605_bluebells/PICT2578.JPG, /Users/kevin/Google Drive/photos/200604_PalmSunday/PICT2537.JPG]
d927521823e69450702f3731fbf564eade0f9117=[/Users/kevin/Google Drive/photos/mixed-keep/DSC01290.JPG, /Users/kevin/Google Drive/photos/200511_FuertaVentura/DSC01306.JPG]
7a727ef88949fb43ecb6168990f21d03e68268f3=[/Users/kevin/Google Drive/photos/mixed-keep/DSC01313.JPG, /Users/kevin/Google Drive/photos/200511_FuertaVentura/DSC01313.JPG]
c1540d91ff85db036ea07354b37cab0f90d99bcc=[/Users/kevin/Google Drive/photos/201003_KevinsMobile/Image003.jpg, /Users/kevin/Google Drive/photos/200901_AliasBirthday/IMG_5222.JPG]
26361912e52b55107ab32a4427713059abb65425=[/Users/kevin/Google Drive/photos/200504/PICT1442.JPG, /Users/kevin/Google Drive/photos/200503_Lanyon/PICT1442.JPG]
bc532d6bc7c0a0f3c0093691528c293d499aba77=[/Users/kevin/Google Drive/photos/200712_Chistmas/Originals/IMG_0139.1.jpg, /Users/kevin/Google Drive/photos/200712_Chistmas/Originals/IMG_0116.2.jpg]
f56d0e90ff5bc2abc0f4efee35d84a35f6076b3a=[/Users/kevin/Google Drive/photos/200704_Newborn/Image014_1.jpg, /Users/kevin/Google Drive/photos/200704_Newborn/Image015.jpg]
12730 ms

     */

}