package business.hibernate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.LockMode;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.exception.ConstraintViolationException;
import net.sf.hibernate.exception.GenericJDBCException;
import business.hibernate.exception.DoublonException;
import business.hibernate.exception.ForeignKeyException;
import business.hibernate.exception.PersistanceException;

/**
 * @author Nicolas Ricard
 * 
 */
public class PersistentObjectDAO  {

    
	public void insert(PersistentObject objet) throws PersistanceException,DoublonException {
		
        Session session = null ;
        Transaction transaction = null;
        try {
            session = HibernateSessionFactory.currentSession();
            transaction = session.beginTransaction();

            session.save(objet);
            
            transaction.commit();
			
		} catch (ConstraintViolationException cve) {
            rollback(transaction);
            if (cve.getErrorCode()==1)
				throw new DoublonException(cve.getMessage());
			throw new PersistanceException(cve.getMessage(),cve);
		} catch (HibernateException he) {
		    rollback(transaction);
            throw new PersistanceException(he.getMessage(),he);
		} finally {
			try {
				if (session!=null && session.isOpen()) 
					HibernateSessionFactory.closeSession();				
			} catch (HibernateException he) {
				throw new PersistanceException(he.getMessage(),he);
			}
		}
	}

    public void insert(PersistentObject objet, Session session) throws HibernateException {
        session.save(objet);
    }    
	
	public void update(PersistentObject objet) throws PersistanceException {
		
        Session session = null ;
        Transaction transaction = null;
        try {
            session = HibernateSessionFactory.currentSession();
            transaction = session.beginTransaction();
            
            session.update(objet);
            
            transaction.commit();

        } catch (HibernateException he) {
            rollback(transaction);
			throw new PersistanceException(he.getMessage(),he);
		} finally {
			try {
				if (session!=null && session.isOpen()) 
					HibernateSessionFactory.closeSession();				
			} catch (HibernateException he) {
				throw new PersistanceException(he.getMessage(),he);
			}
		}
	}	
	

    public void update(PersistentObject objet, Session session) throws HibernateException {
            session.update(objet);
    }    
    
	
	protected PersistentObject getForUpdate(Class classe, Serializable id) throws PersistanceException {
		
		Session session = null ;
		PersistentObject objet =null;
		try {
			session = HibernateSessionFactory.currentSession();
			objet = (PersistentObject) session.get(classe, id, LockMode.UPGRADE);
			
		} catch (HibernateException he) {
			throw new PersistanceException(he.getMessage(),he);
		} finally {
			try {
				if (session!=null && session.isOpen()) HibernateSessionFactory.closeSession();				
			} catch (HibernateException he) {
				throw new PersistanceException(he.getMessage(),he);
			}
		}

		return objet;
	
	}		

	/**
	 * 
	 * @param classe
	 * @param id
	 * @return
	 * @throws PersistanceException
	 */
	protected PersistentObject get(Class classe, Serializable id) throws PersistanceException {
		
		Session session = null ;
		PersistentObject objet =null;
		try {
			session = HibernateSessionFactory.currentSession();
			objet = (PersistentObject) session.get(classe, id);
			
		} catch (HibernateException he) {
			throw new PersistanceException(he.getMessage(),he);
		} finally {
			try {
				if (session!=null && session.isOpen()) HibernateSessionFactory.closeSession();				
			} catch (HibernateException he) {
				throw new PersistanceException(he.getMessage(),he);
			}
		}

		return objet;
	
	}		
	
	
	protected List getList(String table) throws PersistanceException {
		
		List liste = executeQuery("from " + table);

		return liste;
	
	}	

	/**
	 * 
	 * @param objet
	 * @throws PersistanceException
	 * @throws ForeignKeyException
	 * @throws SQLException
	 * @throws HibernateException
	 * @throws SQLException
	 * @throws HibernateException
	 */
	public void delete(PersistentObject objet) throws PersistanceException, ForeignKeyException {
		
		Session session = null ;
		Transaction transaction = null;
		try {
			session = HibernateSessionFactory.currentSession();
            transaction = session.beginTransaction();

            session.delete(objet);
			
			transaction.commit();
        } catch (GenericJDBCException se) {
            rollback(transaction);
            if (se.getErrorCode()==2292)
				throw new ForeignKeyException(se.getMessage());
			throw new PersistanceException(se.getMessage(),se);
		} catch (HibernateException he) {
            rollback(transaction);
            throw new PersistanceException(he.getMessage(),he);
		} finally {
			try {
				if (session!=null && session.isOpen()) 
					HibernateSessionFactory.closeSession();				
			} catch (HibernateException he) {
				throw new PersistanceException(he.getMessage(),he);
			}
		}
	}	
	
	/**
	 * 
	 * @param objet
	 * @param session
	 * @throws ForeignKeyException 
	 * @throws PersistanceException
	 * @throws ForeignKeyException
	 */
	public void delete(PersistentObject objet, Session session) throws HibernateException, ForeignKeyException{
        try {
            session.delete(objet);
        } catch (GenericJDBCException se) {
            if (se.getErrorCode()==2292)
                throw new ForeignKeyException(se.getMessage());
            throw new HibernateException(se.getMessage(),se);
        }
	}	

	/**
     * Effectue un delete sous forme de requete SQL
     * @param requete
     * @throws PersistanceException
	 */
    public void delete(String requete) throws PersistanceException , ForeignKeyException{
        
        Session session = null ;
        Transaction transaction = null;
        try {
            session = HibernateSessionFactory.currentSession();
            transaction = session.beginTransaction();

            session.delete(requete);
            
            transaction.commit();
        } catch (GenericJDBCException se) {
            rollback(transaction);
            if (se.getErrorCode()==2292)
                throw new ForeignKeyException(se.getMessage());
            throw new PersistanceException(se.getMessage(),se);
        } catch (HibernateException he) {
            rollback(transaction);
            throw new PersistanceException(he.getMessage(),he);
        } finally {
            try {
                if (session!=null && session.isOpen()) 
                    HibernateSessionFactory.closeSession();             
            } catch (HibernateException he) {
                throw new PersistanceException(he.getMessage(),he);
            }
        }
    }    
    
    
    /**
     * 
     * @param requete
     * @param session
     * @throws PersistanceException
     */
    public void delete(String requete, Session session) throws HibernateException{
        session.delete(requete);
    }    
    
    
	public void saveOrUpdate(PersistentObject objet) throws PersistanceException {
		
		Session session = null ;
		
		try {
			session = HibernateSessionFactory.currentSession();
			session.saveOrUpdate(objet);
			
			session.flush();
			session.connection().commit();
			
		} catch (HibernateException he) {
			throw new PersistanceException(he.getMessage(),he);
		} catch (SQLException se) {
			throw new PersistanceException(se.getMessage(),se);
		} finally {
			try {
				if (session!=null && session.isOpen()) 
					HibernateSessionFactory.closeSession();				
			} catch (HibernateException he) {
				throw new PersistanceException(he.getMessage(),he);
			}
		}
	}	
	


	/**
	 * Execute a query. 
	 * @param query a query expressed in Hibernate's query language
	 * @return a distinct list of instances (or arrays of instances)
	 */
	public List executeQuery(String query) throws PersistanceException {
		List liste =null;
		Session session = null ;
	
		try {
			session = HibernateSessionFactory.currentSession();
		    liste = session.find(query);
		    session.flush();

		} catch (HibernateException he) {
			throw new PersistanceException(he.getMessage(),he);
		} finally {
			try {
				if (session!=null && session.isOpen()) HibernateSessionFactory.closeSession();				
			} catch (HibernateException he) {
				throw new PersistanceException(he.getMessage(),he);
			}
		}

		return liste;
	}
	
	
	/**
	 * Execute a query with parameters. 
	 * @param query a query expressed in Hibernate's query language
	 * @return a distinct list of instances (or arrays of instances)
	 */
    public List executeQuery(String query, Object[] params) throws PersistanceException {
        
        Session session = null ;
        List list = null;
        try {
            session = HibernateSessionFactory.currentSession();
            
            Query sqlQuery = session.createQuery(query);
            for(int i=0; i<params.length; i++)
                setParameterValue(sqlQuery, i, params[i]);
            list = sqlQuery.list();
            
        } catch (HibernateException he) {
            throw new PersistanceException(he.getMessage(),he);
        } finally {
            try {
                if (session!=null && session.isOpen()) HibernateSessionFactory.closeSession();              
            } catch (HibernateException he) {
                throw new PersistanceException(he.getMessage(),he);
            }
        }

        return list;
    }

	
	/**
	 * Permet un simple select d'un element en base
	 */
	protected PersistentObject get(String beanClass, String keyId, Object valueId) throws PersistanceException {
		
		Session session = null ;
		List list = null;
		PersistentObject object = null;
		
		try {
			session = HibernateSessionFactory.currentSession();
			
			String req = "FROM " + beanClass +" obj WHERE obj." + keyId + "= :id" ;

			Query query = session.createQuery(req);
			setParameterValue(query, "id", valueId);
			
			list = query.list();
			
			Iterator iter = list.iterator();
			if ( iter.hasNext() ) {
			    object = (PersistentObject) iter.next(); 
			}
			
		} catch (HibernateException he) {
			throw new PersistanceException(he.getMessage(),he);
		} finally {
			try {
				if (session!=null && session.isOpen()) HibernateSessionFactory.closeSession();				
			} catch (HibernateException he) {
				throw new PersistanceException(he.getMessage(),he);
			}
		}

		return object;
	
	}		
	
	
	/**
	 * Permet de parser des variables dans les requetes
	 * @param query
	 * @param key
	 * @param value
	 */
	protected void setParameterValue(Query query, String key, Object value) {
		if (null == key || null == value) {
			return;
		} else if (value instanceof Boolean) {
			query.setBoolean(key, ((Boolean) value).booleanValue());
		} else if (value instanceof String) {
			query.setString(key, (String) value);
		} else if (value instanceof Integer) {
			query.setInteger(key, ((Integer) value).intValue());
		} else if (value instanceof Long) {
			query.setLong(key, ((Long) value).longValue());
		} else if (value instanceof Float) {
			query.setFloat(key, ((Float) value).floatValue());
		} else if (value instanceof Double) {
			query.setDouble(key, ((Double) value).doubleValue());
		} else if (value instanceof BigDecimal) {
			query.setBigDecimal(key, (BigDecimal) value);
		} else if (value instanceof Byte) {
			query.setByte(key, ((Byte) value).byteValue());
		} else if (value instanceof Calendar) {
			query.setCalendar(key, (Calendar) value);
		} else if (value instanceof Character) {
			query.setCharacter(key, ((Character) value).charValue());
		} else if (value instanceof Timestamp) {
			query.setTimestamp(key, (Timestamp) value);
		} else if (value instanceof Date) {
			query.setDate(key, (Date) value);
		} else if (value instanceof Short) {
			query.setShort(key, ((Short) value).shortValue());
		}
	}	

    
    /**
     * Permet de parser des variables dans les requetes
     * @param query
     * @param key
     * @param value
     */
    protected void setParameterValue(Query query, int key, Object value) {
        if (key<0 || null == value) {
            return;
        } else if (value instanceof Boolean) {
            query.setBoolean(key, ((Boolean) value).booleanValue());
        } else if (value instanceof String) {
            query.setString(key, (String) value);
        } else if (value instanceof Integer) {
            query.setInteger(key, ((Integer) value).intValue());
        } else if (value instanceof Long) {
            query.setLong(key, ((Long) value).longValue());
        } else if (value instanceof Float) {
            query.setFloat(key, ((Float) value).floatValue());
        } else if (value instanceof Double) {
            query.setDouble(key, ((Double) value).doubleValue());
        } else if (value instanceof BigDecimal) {
            query.setBigDecimal(key, (BigDecimal) value);
        } else if (value instanceof Byte) {
            query.setByte(key, ((Byte) value).byteValue());
        } else if (value instanceof Calendar) {
            query.setCalendar(key, (Calendar) value);
        } else if (value instanceof Character) {
            query.setCharacter(key, ((Character) value).charValue());
        } else if (value instanceof Timestamp) {
            query.setTimestamp(key, (Timestamp) value);
        } else if (value instanceof Date) {
            query.setDate(key, (Date) value);
        } else if (value instanceof Short) {
            query.setShort(key, ((Short) value).shortValue());
        }
    }    
    
    /**
     * Permet de faire un rollback sur une transaction et de gerer son exception
     * @param trans
     */
    private void rollback(Transaction trans){
        try {
            trans.rollback();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
    
	
}
