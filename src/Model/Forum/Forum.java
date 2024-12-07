/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Forum;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author user
 */
public class Forum {
    private  List<Discussion> discussions;

    public Forum() {
        this.discussions = new ArrayList<>();
    }

    public void creerDiscussion(Discussion discussion) {
        discussions.add(discussion);
    }

    public List<Discussion> listerDiscussions() {
        return discussions;
    }
}