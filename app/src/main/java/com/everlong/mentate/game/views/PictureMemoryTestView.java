package com.everlong.mentate.game.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.everlong.mentate.R;
import com.everlong.mentate.game.models.Question;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by akshit on 3/25/17.
 */

public class PictureMemoryTestView extends BaseGameView {

    @BindView(R.id.image)
    ImageView image;

    public PictureMemoryTestView(Context context) {
        super(context);
    }

    public PictureMemoryTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PictureMemoryTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init() {
        inflate(getContext(), R.layout.picture_test_layout, this);
        ButterKnife.bind(this);
    }

    @Override
    public void setView(Question question, int questionIndex) {
        super.setView(question, questionIndex);
        FirebaseStorage storage = FirebaseStorage.getInstance();

        StorageReference storageReference = storage.getReference().child(question.getImageUrl());
        Glide.with(getContext())
                .using(new FirebaseImageLoader())
                .load(storageReference)
                .into(image);
    }
}
