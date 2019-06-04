package com.vukasin_prvulovic.micegame.presentation.view.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.vukasin_prvulovic.micegame.R;
import com.vukasin_prvulovic.micegame.data.model.Field;
import com.vukasin_prvulovic.micegame.data.model.state.FieldState;
import com.vukasin_prvulovic.micegame.data.model.state.MiceState;
import com.vukasin_prvulovic.micegame.data.model.state.PlayerState;
import com.vukasin_prvulovic.micegame.presentation.base.view.BaseFragment;
import com.vukasin_prvulovic.micegame.presentation.presenters.BoardFragmentPresenter;
import com.vukasin_prvulovic.micegame.presentation.view.adapter.TipsAdapter;
import com.vukasin_prvulovic.micegame.presentation.view.fragments.abstraction.BoardView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.support.AndroidSupportInjection;

public class BoardFragment extends BaseFragment implements BoardView,View.OnTouchListener,View.OnClickListener,View.OnLongClickListener{

    @Inject
    BoardFragmentPresenter presenter;

    @BindView(R.id.board_fragment_blackboard_player_one_name)TextView playerOne;
    @BindView(R.id.board_fragment_blackboard_player_two_name)TextView playerTwo;
    @BindView(R.id.board_fragment_blackboard_player_one_turn)TextView playerOneTurn;
    @BindView(R.id.Board_fragment_blackboard_player_two_turn)TextView playerTwoTurn;
    @BindView(R.id.player_one_basket)ImageView basketOne;
    @BindView(R.id.board_fragment_basket_player_two)ImageView basketTwo;
    @BindView(R.id.board_fragment_blackboard_number_of_tokens_player_one)TextView numberOfPlayerOne;
    @BindView(R.id.board_fragment_blackboard_number_of_tokens_player_two)TextView numberOfPlayerTwo;
    @BindView(R.id.board_fragment_movable)ImageView movable;
    @BindView(R.id.board_fragment_undo)ImageView undo;
    @BindView(R.id.board_fragment_menu)ImageView users;
    @BindView(R.id.board_fragment_pause)ImageView pause;
    @BindView(R.id.board_fragment_pause_container)FrameLayout container;

    private ConstraintLayout menuContainer;
    private ImageView menuSound;
    private ImageView menuMusic;
    private ImageView menuExit;
    private ConstraintLayout pauseContainer;
    private ConstraintLayout tipsContainer;
    private ViewPager tipsViewPager;
    private ConstraintLayout winnerContainer;

    @Inject
    TipsAdapter tipsAdapter;

    private List<ImageView> fields=new ArrayList<>();

    public static BoardFragment newInstance() {

        Bundle args = new Bundle();

        BoardFragment fragment = new BoardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_board;
    }

    @Override
    protected void initView() {
        presenter.onStart();
        pause.setOnClickListener(this);
        undo.setOnClickListener(this);
        users.setOnClickListener(this);
        container.setOnLongClickListener(this);
        presenter.loadGame();
        initMenu();
        initPause();
        initTips();
        initWinner();
    }

    @Override
    public void setFields(List<String> tags) {
        for (int i = 0; i < tags.size(); i++) {
            if (getView()!=null) {
                ImageView imageView = getView().findViewWithTag(tags.get(i));
                imageView.setOnTouchListener(this);
                fields.add(imageView);
            }
        }
    }

    @Override
    public void setPlayerName(String playerOneString, String playerTwoString) {
        playerOne.setText(playerOneString);
        playerTwo.setText(playerTwoString);
    }

    @Override
    public void setPlayerTurn(PlayerState nextPlayerState) {
        if (nextPlayerState == PlayerState.PLAYER_ONE){
            playerOneTurn.setText("Your Turn");
            playerTwoTurn.setText("");
        }else{
            playerTwoTurn.setText("Your Turn");
            playerOneTurn.setText("");
        }
    }

    @Override
    public void setNumberOfTokens(String playerOneTokens, String playerTwoTokens) {
        if (playerOneTokens.equals("more: 0")){
            basketOne.setImageResource(R.drawable.empty_basket);
        }else{
            basketOne.setImageResource(R.drawable.basket_player_one);
        }
        if (playerTwoTokens.equals("more: 0")){
            basketTwo.setImageResource(R.drawable.empty_basket);
        }else{
            basketTwo.setImageResource(R.drawable.basket_player_two);
        }
        numberOfPlayerOne.setText(playerOneTokens);
        numberOfPlayerTwo.setText(playerTwoTokens);
    }

    @Override
    public void setBasketsOn() {
        basketOne.setOnTouchListener(this);
        basketTwo.setOnTouchListener(this);
    }

    @Override
    public void setBasketsOff() {
        basketOne.setOnTouchListener(null);
        basketTwo.setOnTouchListener(null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onStop();
        setNullListener();
        pause.setOnClickListener(null);
        undo.setOnClickListener(null);
        users.setOnClickListener(null);
        container.setOnLongClickListener(null);
        tipsAdapter.release();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setNullListener(){
        for (ImageView field : fields) {
            field.setOnTouchListener(null);
        }
        basketTwo.setOnTouchListener(null);
        basketOne.setOnTouchListener(null);
        menuSound.setOnClickListener(null);
        menuMusic.setOnClickListener(null);
        menuExit.setOnClickListener(null);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                presenter.actionDown((String)view.getTag(),
                        (int)motionEvent.getRawX(),(int)motionEvent.getRawY());
                return true;
            case MotionEvent.ACTION_MOVE:
                presenter.actionMove((int)motionEvent.getRawX(),(int)motionEvent.getRawY());
                return true;
            case MotionEvent.ACTION_UP:
                String tag=doesViewContainPoint((int)motionEvent.getRawX(),(int)motionEvent.getRawY());
                presenter.actionUp(tag);
                return true;
        }

        return false;
    }

    private String doesViewContainPoint(int x, int y){
        int i=0;
        for (;i<fields.size();i++){
            int[] location=new int[2];
            fields.get(i).getLocationOnScreen(location);
            Rect rect=new Rect(location[0],location[1],
                    location[0]+fields.get(i).getWidth(),
                    location[1]+fields.get(i).getHeight());
            if (rect.contains(x,y)){
                break;
            }
        }
        if (i<fields.size()) {
            return (String) fields.get(i).getTag();
        }else{
            return null;
        }
    }

    @Override
    public void setOnMovable(int x, int y, PlayerState playerState){
        movable.setX(x-movable.getWidth()/2);
        movable.setY(y-((ViewGroup)(getView().getParent())).getY()-movable.getHeight()-movable.getHeight()/2);
        movable.setVisibility(View.VISIBLE);
        if (playerState==PlayerState.PLAYER_ONE)
            movable.setImageResource(R.drawable.ic_player_one_move);
        else
            movable.setImageResource(R.drawable.ic_player_two_move);
    }

    @Override
    public void settingMovable(int x,int y){
        movable.setX(x-movable.getWidth()/2);
        movable.setY(y-((ViewGroup)(getView().getParent())).getY()-movable.getHeight()-movable.getHeight()/2);
    }

    @Override
    public void setOffMovable(){
        movable.setVisibility(View.INVISIBLE);
        movable.setImageResource(0);
        movable.setX(0);
        movable.setY(0);
    }

    @Override
    public void showTips() {
        if (container.getChildCount()>0){
            container.removeAllViews();
        }
        container.addView(tipsContainer);
        container.setVisibility(View.VISIBLE);
    }

    @Override
    public void drawBoard(Map<String, Field> fieldMap) {
        for (Field miceField : fieldMap.values()) {
            String miceId=miceField.getId();
            for (ImageView field : fields) {
                if (miceId.equals((String)field.getTag())){
                    if (miceField.getFieldState()== FieldState.PLAYER_ONE){
                        if (miceField.getMiceState()== MiceState.IN_MICE){
                            field.setBackgroundResource(R.drawable.background_mice_state);
                            field.setImageResource(R.drawable.ic_player_one_mice);
                        }else{
                            field.setImageResource(R.drawable.ic_player_one);
                            field.setBackgroundResource(0);
                        }
                    }else if (miceField.getFieldState()==FieldState.PLAYER_TWO){
                        if (miceField.getMiceState()== MiceState.IN_MICE){
                            field.setBackgroundResource(R.drawable.background_mice_state);
                            field.setImageResource(R.drawable.ic_player_two_mice);
                        }else{
                            field.setImageResource(R.drawable.ic_player_two);
                            field.setBackgroundResource(0);
                        }
                    }else{
                        field.setImageResource(0);
                        field.setBackgroundResource(0);
                    }
                }
            }
        }
    }

    @Override
    public void clearField(String tag) {
        for (ImageView field : fields) {
            if (field.getTag().equals(tag)){
                field.setImageResource(0);
            }
        }
    }

    @Override
    public void onClick(View view) {
        presenter.playClickSound();
        switch (view.getId()){
            case R.id.board_fragment_undo:
                presenter.undo();
                break;
            case R.id.board_fragment_pause:
                container.addView(pauseContainer);
                if (container.getVisibility()==View.GONE){
                    container.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.board_fragment_menu:
                container.addView(menuContainer);
                if (container.getVisibility()==View.GONE){
                    container.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.board_fragment_menu_exit:
                presenter.exit();
                break;
            case R.id.board_fragment_menu_music:
                presenter.saveMusicPermission();
                break;
            case R.id.board_fragment_menu_sound:
                presenter.saveSoundPermission();
                break;
        }
    }

    @Override
    public boolean onLongClick(View view) {
        presenter.playClickSound();
        if (container.getChildCount()>0){
            container.removeAllViews();
        }
        if (container.getVisibility()==View.VISIBLE){
            container.setVisibility(View.GONE);
        }
        return true;
    }

    @Override
    public void showWinner() {
        if (container.getChildCount()>0){
            container.removeAllViews();
        }
        container.addView(winnerContainer);
        container.setVisibility(View.VISIBLE);
    }

    private void initMenu(){
        menuContainer=(ConstraintLayout) getLayoutInflater().inflate(R.layout.fragment_board_menu, container,false);
        menuSound=menuContainer.findViewById(R.id.board_fragment_menu_sound);
        menuMusic=menuContainer.findViewById(R.id.board_fragment_menu_music);
        menuExit=menuContainer.findViewById(R.id.board_fragment_menu_exit);
        menuSound.setOnClickListener(this);
        menuMusic.setOnClickListener(this);
        menuExit.setOnClickListener(this);
    }

    @Override
    public void setMusicIcon(boolean set) {
        if (set){
            menuMusic.setImageResource(R.drawable.ic_music);
        }else{
            menuMusic.setImageResource(R.drawable.ic_no_music);
        }
    }

    @Override
    public void setSoundIcon(boolean set) {
        if (set){
            menuSound.setImageResource(R.drawable.ic_sound);
        }else{
            menuSound.setImageResource(R.drawable.ic_no_sound);
        }
    }

    private void initPause(){
        pauseContainer=(ConstraintLayout) getLayoutInflater().inflate(R.layout.fragment_board_pause,container,false);
    }

    private void initTips(){
        tipsContainer=(ConstraintLayout)getLayoutInflater().inflate(R.layout.fragment_board_tips,container,false);
        tipsViewPager=tipsContainer.findViewById(R.id.fragment_board_view_pager);
        tipsViewPager.setAdapter(tipsAdapter);
        tipsViewPager.setOnLongClickListener(this);
    }

    private void initWinner() {
        winnerContainer=(ConstraintLayout)getLayoutInflater().inflate(R.layout.fragment_board_winner,container,false);
    }
}
