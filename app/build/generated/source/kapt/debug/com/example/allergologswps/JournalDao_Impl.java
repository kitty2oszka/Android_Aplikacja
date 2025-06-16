package com.example.allergologswps;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class JournalDao_Impl implements JournalDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<JournalEntry> __insertionAdapterOfJournalEntry;

  public JournalDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfJournalEntry = new EntityInsertionAdapter<JournalEntry>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `JournalEntry` (`nausea`,`rash`,`stomachache`,`vomiting`,`cough`,`swelling`,`note`,`timestamp`,`id`) VALUES (?,?,?,?,?,?,?,?,nullif(?, 0))";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final JournalEntry entity) {
        final int _tmp = entity.getNausea() ? 1 : 0;
        statement.bindLong(1, _tmp);
        final int _tmp_1 = entity.getRash() ? 1 : 0;
        statement.bindLong(2, _tmp_1);
        final int _tmp_2 = entity.getStomachache() ? 1 : 0;
        statement.bindLong(3, _tmp_2);
        final int _tmp_3 = entity.getVomiting() ? 1 : 0;
        statement.bindLong(4, _tmp_3);
        final int _tmp_4 = entity.getCough() ? 1 : 0;
        statement.bindLong(5, _tmp_4);
        final int _tmp_5 = entity.getSwelling() ? 1 : 0;
        statement.bindLong(6, _tmp_5);
        if (entity.getNote() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getNote());
        }
        statement.bindLong(8, entity.getTimestamp());
        statement.bindLong(9, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final JournalEntry entry, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfJournalEntry.insert(entry);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAll(final Continuation<? super List<JournalEntry>> $completion) {
    final String _sql = "SELECT * FROM JournalEntry ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<JournalEntry>>() {
      @Override
      @NonNull
      public List<JournalEntry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfNausea = CursorUtil.getColumnIndexOrThrow(_cursor, "nausea");
          final int _cursorIndexOfRash = CursorUtil.getColumnIndexOrThrow(_cursor, "rash");
          final int _cursorIndexOfStomachache = CursorUtil.getColumnIndexOrThrow(_cursor, "stomachache");
          final int _cursorIndexOfVomiting = CursorUtil.getColumnIndexOrThrow(_cursor, "vomiting");
          final int _cursorIndexOfCough = CursorUtil.getColumnIndexOrThrow(_cursor, "cough");
          final int _cursorIndexOfSwelling = CursorUtil.getColumnIndexOrThrow(_cursor, "swelling");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final List<JournalEntry> _result = new ArrayList<JournalEntry>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final JournalEntry _item;
            final boolean _tmpNausea;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfNausea);
            _tmpNausea = _tmp != 0;
            final boolean _tmpRash;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfRash);
            _tmpRash = _tmp_1 != 0;
            final boolean _tmpStomachache;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfStomachache);
            _tmpStomachache = _tmp_2 != 0;
            final boolean _tmpVomiting;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfVomiting);
            _tmpVomiting = _tmp_3 != 0;
            final boolean _tmpCough;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfCough);
            _tmpCough = _tmp_4 != 0;
            final boolean _tmpSwelling;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfSwelling);
            _tmpSwelling = _tmp_5 != 0;
            final String _tmpNote;
            if (_cursor.isNull(_cursorIndexOfNote)) {
              _tmpNote = null;
            } else {
              _tmpNote = _cursor.getString(_cursorIndexOfNote);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item = new JournalEntry(_tmpNausea,_tmpRash,_tmpStomachache,_tmpVomiting,_tmpCough,_tmpSwelling,_tmpNote,_tmpTimestamp,_tmpId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
