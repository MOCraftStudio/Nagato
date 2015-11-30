package org.mocraft.Nagato;

public class LevyData {

	public static int time[] = {
			900, 1800, 1200, 3000, 5400,
			2400, 3600, 10800,

			14400, 5400, 18000, 28800, 14400,
			21600, 43200
	};
	public static String name[] = {
			"", 
			"01練習航海", "02長距離練習航海", "03警備任務", "04対潜警戒任務", "05海上護衛任務",
			"06防空射撃演習", "07観艦式予行", "08観艦式",
			
			"09タンカー護衛任務", "10強行偵察任務", "11ボーキサイト輸送任務", "12資源輸送任務","13鼠輸送作戦",
			"14包囲陸戦隊撤収作戦", "15囮機動部隊支援作戦", "16艦隊決戦援護作戦"
	};
	
	public static String getImg(int i) {
		return ("img/Attack/Levy/" + i + ".png");
	}
	
}
