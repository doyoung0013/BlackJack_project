package blackjack;

import java.util.*;

class BlackJack{
	static final Map<String, Integer> cardList = new HashMap<String, Integer>();
	static final String[] cardName = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
	static List<String> dealerAll = new ArrayList<String>();
	static List<String> playerAll = new ArrayList<String>();
	static int dealerTotal = 0;
	static int playerTotal = 0;
	
	public BlackJack() {
		for(int i=1; i<10; i++) {
			cardList.put(cardName[i], i+1);
		}
		cardList.put("A", 11);
		cardList.put("J", 10);
		cardList.put("Q", 10);
		cardList.put("K", 10);
	}
}

public class BlackJackTest {

	public static void main(String[] args) {
		BlackJack blackjack = new BlackJack(); //cardList 값 초기화 위해
		boolean end = false; //while문 제어
		
		System.out.println("블랙잭 게임을 시작합니다.");
		System.out.println("지금부터 딜러 카드를 배분하겠습니다.");
		Scanner sc = new Scanner(System.in);
		String dealerCard = dealerCardSelect();
		System.out.println("딜러의 첫 번째 카드: "+dealerCard);
		dealerCardSelect();
		System.out.println("딜러의 두 번째 카드: ?");
		
		System.out.println("지금부터 플레이어의 카드를 배분하겠습니다.");
		String playerCard = playerCardSelect();
		System.out.println("당신의 첫 번째 카드: "+playerCard);
		playerCard = playerCardSelect();
		System.out.println("당신의 두 번째 카드: "+playerCard);
		
		if(BlackJack.dealerTotal>21) {
			System.out.println("딜러의 카드 합이 21이 넘어 게임이 종료됩니다.");
			end = true;
		}
		
		while(!end) {
			//딜러 턴
			System.out.println("-----------------------------------");
			System.out.println("딜러의 차례입니다.");
			if(BlackJack.dealerTotal<17) {
				System.out.println("딜러 카드 한 장을 뽑습니다.");
				dealerCardSelect();
				if(BlackJack.dealerTotal>21) {
					System.out.println("딜러의 카드 합이 21이 넘어 게임이 종료됩니다.");
					end = true; break; //break >> while문 탈출(플레이어 턴 없이 끝나게)
				}
			}else{
				System.out.println("딜러는 카드를 뽑지않았습니다.");
			}
			
			//플레이어 턴
			System.out.println("-----------------------------------");
			System.out.println("플레이어의 차례입니다.");
			System.out.println("현재 당신의 카드 합 : "+BlackJack.playerTotal);
			System.out.println("카드를 더 뽑으시려면 0을, 그만두시려면 1을 입력해주세요.");
			int gameContinue = sc.nextInt();
			switch(gameContinue) {
			case 0:
				System.out.println("플레이어 카드 한 장을 뽑습니다.");
				playerCard = playerCardSelect();
				System.out.println("당신의 새로 뽑은 카드: "+playerCard);
				if(BlackJack.playerTotal>21) {
					System.out.println("플레이어 카드의 합이 21이 넘어 종료됩니다.");
					end = true;
				}
				break;
			case 1:
				System.out.println("게임이 종료됩니다.");
				end = true; break;
			default:
				System.out.println("잘못 입력하셨습니다.");
			}
			
		}
		sc.close();
		
		System.out.println("---------------결과 산출 중---------------");
		System.out.println("딜러의 카드: "+BlackJack.dealerAll.toString());
		System.out.println("당신의 카드: "+BlackJack.playerAll.toString());
		System.out.println("딜러의 카드 합 : "+BlackJack.dealerTotal);
		System.out.println("당신의 카드 합 : "+BlackJack.playerTotal);
		if(BlackJack.dealerTotal>21) {
			System.out.println("카드합 21이 넘어 딜러가 패배했습니다.");
		}else if(BlackJack.playerTotal>21) {
			System.out.println("카드합 21이 넘어 플레이어가 패배했습니다.");
		}else {
			if(BlackJack.dealerTotal>BlackJack.playerTotal) {
				System.out.println("딜러가 이겼습니다.");
			}else if(BlackJack.playerTotal>BlackJack.dealerTotal) {
				System.out.println("당신이 이겼습니다.");
			}else {
				System.out.println("비겼습니다.");
			}
		}
		
		
	}
	
	//딜러 카드를 한 장 뽑은 후 카드저장, 누적값 구하는 메서드
	private static String dealerCardSelect() {
		Random ran = new Random();
		int randomNum = ran.nextInt(13);//카드 종류는 13가지로 고정
		String selectCard = BlackJack.cardName[randomNum];
		BlackJack.dealerAll.add(selectCard);
		BlackJack.dealerTotal += BlackJack.cardList.get(selectCard);
		return selectCard;
	}
	//플레이어 카드를 한 장 뽑은 후 카드저장, 누적값 구하는 메서드
	private static String playerCardSelect() {
		Random ran = new Random();
		int randomNum = ran.nextInt(13);
		String selectCard = BlackJack.cardName[randomNum];
		BlackJack.playerAll.add(selectCard);
		BlackJack.playerTotal += BlackJack.cardList.get(selectCard);
		return selectCard;
	}

}
