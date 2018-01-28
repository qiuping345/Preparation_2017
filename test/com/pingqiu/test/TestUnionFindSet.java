package com.pingqiu.test;

import static org.junit.Assert.*;
import com.pingqiu.UnionFindSet;

import org.junit.Test;

public class TestUnionFindSet {

    @Test
    public void test() {
        UnionFindSet ufs = new UnionFindSet(5);
        ufs.union(2, 3);
        ufs.union(3, 4);
        assert(3 == ufs.count());
        
        ufs.union(2, 1);
        assert(2 == ufs.count());
    }

}
