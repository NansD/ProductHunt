package fr.ec.producthunt.data.database;

import android.database.Cursor;
import android.util.Log;

import fr.ec.producthunt.data.model.Post;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * @author Mohammed Boukadir  @:mohammed.boukadir@gmail.com
 */
public class PostDao {

  private final ProductHuntDbHelper productHuntDbHelper;

  public PostDao(ProductHuntDbHelper productHuntDbHelper) {
    this.productHuntDbHelper = productHuntDbHelper;
  }

  public long save(Post post) {
    return productHuntDbHelper.getWritableDatabase()
        .replace(DataBaseContract.PostTable.TABLE_NAME, null, post.toContentValues());
  }

  public List<Post> retrievePosts() {


    Cursor cursor = productHuntDbHelper.getReadableDatabase()
        .query(DataBaseContract.PostTable.TABLE_NAME,
            DataBaseContract.PostTable.PROJECTIONS,
            null, null, null, null, null);

    List<Post> posts = new ArrayList<>(cursor.getCount());

    if (cursor.moveToFirst()) {
      do {

        Post post = new Post();

        post.setId(cursor.getInt(0));
        post.setTitle(cursor.getString(1));
        post.setSubTitle(cursor.getString(2));
        post.setImageUrl(cursor.getString(3));
        post.setPostUrl(cursor.getString(4));
        post.setCommentNumber(cursor.getString(5));

        posts.add(post);


      } while (cursor.moveToNext());
    }

    cursor.close();

    return posts;
  }
}
