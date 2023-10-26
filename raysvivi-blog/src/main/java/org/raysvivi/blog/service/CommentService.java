package org.raysvivi.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.raysvivi.blog.dao.CommentMapper;
import org.raysvivi.blog.constant.Constant;
import org.raysvivi.blog.model.user.CommentInfo;
import org.spider.common.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    public void add(HttpServletRequest request,CommentInfo commentInfo){
        if(StringUtils.isEmpty(commentInfo.getWriterName())){
            commentInfo.setWriterName(HttpUtil.getClientIp(request));
        }
        commentMapper.insert(commentInfo);
    }

    public void delete(String id,String relativeIdString, String moduleId){
        //TODO 待写逻辑
        //List<CommentInfo>
        commentMapper.updateStatus(id);
    }
    public List<CommentInfo> getList(String moduleId,String relativeId){

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("f_module_id",moduleId);
        queryWrapper.eq("delete_flag", Constant.Common.EFFECTIVE);
        queryWrapper.orderByDesc("create_time");
        if(StringUtils.isNotEmpty(relativeId)){
            queryWrapper.eq("f_relative_id",relativeId);
        }
        List<CommentInfo> comments = commentMapper.selectList(queryWrapper);
        return buildCommentTree(comments);
    }

    public List<CommentInfo> getConditionList(String moduleId,List<String> relativeIds){
        return commentMapper.getConditionList(moduleId,relativeIds);
    }
    private List<CommentInfo> buildCommentTree(List<CommentInfo> comments){
        if(CollectionUtils.isEmpty(comments)){
            return null;
        }
        List<CommentInfo> list = comments.stream().filter(item -> Constant.Common.ROOT_INDEX.equals(item.getParentId())).collect(Collectors.toList());

        //根据parentId进行分组
        Map<String, List<CommentInfo>> map = comments.stream().collect(Collectors.groupingBy(CommentInfo::getParentId));
        recursionFnTree(list, map);
        return list;
    }

    /**
     * 递归遍历节点
     * @param list
     * @param map
     */
    public static void recursionFnTree(List<CommentInfo> list, Map<String, List<CommentInfo>> map){
        for (CommentInfo treeSelect : list) {
            List<CommentInfo> childList = map.get(treeSelect.getId());
            treeSelect.setChildren(childList);
            if (null != childList && 0 < childList.size()){
                recursionFnTree(childList,map);
            }
        }
    }
}
