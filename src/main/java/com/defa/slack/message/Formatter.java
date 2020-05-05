package com.defa.slack.message;

public interface Formatter {
   default String format() {
       return toString();
   };
}
