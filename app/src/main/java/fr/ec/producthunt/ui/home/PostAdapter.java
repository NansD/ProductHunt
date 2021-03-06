package fr.ec.producthunt.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import fr.ec.producthunt.R;
import fr.ec.producthunt.data.model.Post;
import java.util.Collections;
import java.util.List;

import static android.content.ContentValues.TAG;

public class PostAdapter extends BaseAdapter {

  private List<Post> dataSource = Collections.emptyList();

  public PostAdapter() {
  }

  @Override public int getCount() {
    return dataSource.size();
  }

  @Override public Object getItem(int position) {
    return dataSource.get(position);
  }

  @Override public long getItemId(int position) {
    return position;
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {

    ViewHolder viewHolder;

    boolean isBigView = getViewType(position);

    if(!isBigView) {
      if (convertView == null) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        viewHolder = new ViewHolder();
        viewHolder.title = convertView.findViewById(R.id.title);
        viewHolder.subTitle = convertView.findViewById(R.id.sub_title);
        viewHolder.postImage = convertView.findViewById(R.id.img_product);
        viewHolder.commentsNumber = convertView.findViewById(R.id.comments_counter);


        convertView.setTag(viewHolder);
      } else {

        viewHolder = (ViewHolder) convertView.getTag();
      }

      Post post = dataSource.get(position);
      viewHolder.title.setText(post.getTitle());
      viewHolder.subTitle.setText(post.getSubTitle());
      Log.d(TAG, "getView: "+ post.getCommentNumber());
      viewHolder.commentsNumber.setText(post.getCommentNumber() + " comments");


      Picasso.with(parent.getContext())
              .load(post.getImageUrl())
              .centerCrop()
              .fit()
              .into(viewHolder.postImage);
    } else {
      if (convertView == null) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);

        viewHolder = new ViewHolder();
        viewHolder.title = convertView.findViewById(R.id.title);
        viewHolder.subTitle = convertView.findViewById(R.id.sub_title);
        viewHolder.postImage = convertView.findViewById(R.id.img_product);
        viewHolder.commentsNumber = convertView.findViewById(R.id.comments_counter);

        convertView.setTag(viewHolder);
      } else {

        viewHolder = (ViewHolder) convertView.getTag();
      }

      Post post = dataSource.get(position);
      viewHolder.title.setText(post.getTitle());
      viewHolder.subTitle.setText(post.getSubTitle());
      viewHolder.commentsNumber.setText(post.getCommentNumber() + " comments");


      Picasso.with(parent.getContext())
              .load(post.getImageUrl())
              .centerCrop()
              .fit()
              .into(viewHolder.postImage);
    }



    return convertView;
  }

  private boolean getViewType(int itemPosition) {
    return itemPosition == 0;
  }

  public void showPosts(List<Post> posts) {
    dataSource = posts;

    notifyDataSetChanged();
  }

  private static class ViewHolder {
    TextView title;
    TextView subTitle;
    TextView commentsNumber;
    ImageView postImage;
  }
}
