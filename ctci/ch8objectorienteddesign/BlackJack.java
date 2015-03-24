package ch8objectorienteddesign;

import java.util.ArrayList;
import java.util.Random;

enum Suit {
  CLUB(0), DIAMOND(1), HEART(2), SPADE(3);
  private int val;
  private Suit(int v) {val = v;}
  public int getValue() { return val; }
  public static Suit getSuit(int v) { return new Suit(v); }
}

class Deck<T extends Card> {
  private ArrayList<T> cards;
  private int dealtIndex = 0; // mark the first undealt card, that is, point to the next card given to a player

  public void setDeck(ArrayList<T> cards) {this.cards = cards;}

  public void shuffle() {
    for (int i = 0; i < cards.size(); i++) {
      int j = new Random().nextInt(i);
      T card1 = cards.get(i);
      T card2 = cards.get(j);
      cards.set(i, card2);
      cards.set(j, card1);
    }
  }
  public int remainingCards() {return cards.size() - dealtIndex;}
  /*
   * Take a hand of cards from the deck
   */
  public T[] dealHand(int number) {
    if (remainingCards() < number)
      return null;
    T[] hand = (T[]) new Card[number];
    int count = 0;
    while (count < number) {
      T card = (T) dealCard();
      if (card != null)
        hand[count++] = card;
    }
    return hand;
  }
  /*
   * Take a card from the deck
   */
  public T dealCard() {
    if (remainingCards() == 0)
      return null;
    T card = cards.get(dealtIndex);
    card.markUnavailable();
    dealtIndex++;
    return card;
  }
}

public class Hand<T extends Card> {
  protected ArrayList<T> cards;

  public Hand() {
    cards = new ArrayList<T>();
  }
  public int score() {
    int score = 0;
    for (T card : cards)
      score += card.value();
    return score;
  }
  public void addCard(T card) {
    cards.add(card);
  }
}

public abstract class Card {
  private boolean available = true;
  protected Suit suit;
  protected int faceValue;

  public Card(int f, int s) {
    faceValue = f;
    suit = Suit.getSuit(s);
  }
  public abstract int value();
  public Suit suit() { return suit; }
  public boolean isAvailable() { return available;}
  public void setAvailability(boolean a) {available = a;}
}

class BlackJackCard extends Card {
  public int value() {
    if(faceValue >= 1 && faceValue <= 10) return faceValue;
    else return 10;
  }
  boolean isAce() {return faceValue == 1;}
  boolean isFaceCard() {return (faceValue == 11 || faceValue == 12 || faceValue == 13);}
  int maxValue() { if(isAce()) return 11; else return value();}
  int minValue() { if(isAce()) return 1; else return value();}
}

class BlackJackHand extends Hand<BlackJackCard> {

  public int score() {
    ArrayList<Integer> scores = possibleScores();
    int minOver = Integer.MAX_VALUE; // min score over 21
    int maxUnder = Integer.MIN_VALUE; // max score under 21
    for (int score : scores) {
      if (score > 21 && score < minOver)
        minOver = score;
      else if (score <= 21 && score > maxUnder)
        maxUnder = score;
    }
    return maxUnder == Integer.MIN_VALUE ? minOver : maxUnder;
  }

  private ArrayList<Integer> possibleScores() {
    ArrayList<Integer> scores = new ArrayList<Integer>();
    if (cards.size() == 0) {
      return scores;
    }
    for (BlackJackCard card : cards) {
      addCardToScoreList(card, scores);
    }
    return scores;
  }

  private void addCardToScoreList(BlackJackCard card, ArrayList<Integer> scores) {
    if (scores.size() == 0) {
      scores.add(0);
    }
    int length = scores.size();
    for (int i = 0; i < length; i++) {
      int score = scores.get(i);
      scores.set(i, score + card.minValue());
      if (card.minValue() != card.maxValue()) {
        scores.add(score + card.maxValue());
      }
    }
  }
  public boolean busted() {
    return score() > 21;
  }

  public boolean is21() {
    return score() == 21;
  }

  public boolean isBlackJack() {
    if (cards.size() != 2) {
      return false;
    }
    BlackJackCard first = cards.get(0);
    BlackJackCard second = cards.get(1);
    return (first.isAce() && second.isFaceCard()) || (second.isAce() && first.isFaceCard());
  }
}





